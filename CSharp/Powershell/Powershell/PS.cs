using System;
using System.Collections.Generic;
using System.Linq;
using System.Xml;
using System.Text;
using System.Management.Automation;
using System.Management.Automation.Runspaces;
using System.Collections.ObjectModel;

/**
    @author Senthil kumar Murugesan
    @version 1.0 26/05/2013
*/

namespace Powershell
{
    public class PS
    {
        private Runspace runSpace = null;

        private PS() { }

        private static Dictionary<String, PS> instances = new Dictionary<String, PS>();
        private RunspaceConfiguration rsConfig;

        public static PS getInstance(String scriptDirectory)
        {
            if (instances.ContainsKey(scriptDirectory))
                return instances[scriptDirectory];
            else
            {
                PS ps1 = new PS(scriptDirectory);
                instances.Add(scriptDirectory, ps1);
                return ps1;
            }
        }

        private PS(String scriptDirectory)
        {
            System.Environment.CurrentDirectory = scriptDirectory;
            rsConfig = RunspaceConfiguration.Create();
            //PSSnapInException snapInException;
            //var snapinInfo = rsConfig.AddPSSnapIn("Microsoft.Exchange.Management.PowerShell.E2010", out snapInException);

            runSpace = RunspaceFactory.CreateRunspace(rsConfig);
            runSpace.Open();
        }

        public void addPSSnapIn(String snapIn)
        {
            PSSnapInException snapInException;
            runSpace.RunspaceConfiguration.AddPSSnapIn(snapIn, out snapInException);
        }

        public String execute(String command, String paramters)
        {
            if (runSpace == null)
            {
                throw new Exception("Runspace cannot be null, please make sure the correct constructor is used");
            }
            Command commandToExecute = new Command(command);
            Pipeline pipeline = runSpace.CreatePipeline();

            if (!paramters.Equals(""))
            {
                XmlDocument xml = new XmlDocument();
                xml.LoadXml(paramters);
                XmlNodeList xnList = xml.SelectNodes("/Parameters/Parameter");

                foreach (XmlNode xn in xnList)
                {
                    String name = null, value = null;
                    if (xn["Name"] != null)
                        name = xn["Name"].InnerText;
                    else
                        throw new Exception("Parameter name not found");

                    if (xn["Name"] != null)
                        value = xn["Value"].InnerText;
                    else
                        throw new Exception("Paramter value not found");

                    if (name.Equals("null")) name = null;
                    if (value.Equals("null")) value = null;
                    commandToExecute.Parameters.Add(name, value);
                }
            }
            pipeline.Commands.Add(commandToExecute);
            Collection<PSObject> results = pipeline.Invoke();
            String result = "";
            foreach (PSObject psObject in results)
            {
                result = result + psObject.ToString();
            }

            //Console.WriteLine("Done");
            return result;
        }

    }
}
