package com.cordys.coe.jni;

/**
 * This class provides the JNI to interact with the windows powershell.
 * 
 * @author Senthil kumar Murugesan
 * @version 1.0
 */

public class PSBridge {
	private String scriptDir = "";

	/**
	 * @param scriptDir
	 *            Provide the location of the directory where all your script
	 *            files are located. Ex. "e:/psscripts". <br/>
	 *            This needs to be a valid location, otherwise the PSBridge
	 *            would not be initialized & cannot be used.
	 */
	public PSBridge(String scriptDir) {
		super();
		this.scriptDir = scriptDir;
	}

	static {
		System.loadLibrary("PSBridge");
	}

	private native String execute(String command, String parameters,
			String scriptDir);
	
	private native String addSnapIn(String snapIn,
			String scriptDir);

	/**
	 * @param command
	 *            Pass the powershell command or script name to execute. <br/>
	 *            In case of command name, just provide the command name. <br/>
	 *            Ex. "Get-Process" <br/>
	 * <br/>
	 *            In case of script name, provide the complete path to the
	 *            script file. If the script file exists in the scriptDir,
	 *            provide the script file with "./" in the front. <br/>
	 *            Ex. " e:/psscripts/script.ps1" or  "./Script.ps1".
	 * 
	 * @param parameters
	 *            Pass the parameters, as name-value pair in XML format. <br/>
	 * 
	 *            <pre>
	 * Ex. 
	 * {@code
	 * <Parameters>
	 *   <Parameter>
	 *     <Name>DistinguishedName</Name>
	 *     <Value>CN=nologon,CN=Users,DC=xcorcap,DC=tst</Value>
	 *   </Parameter>
	 * </Parameters>
	 * }
	 * </pre>
	 * 
	 * <br/>
	 *            If the parameter has no name, pass <b>null</b>. <br/>
	 * 
	 *            <pre>
	 * Ex. 
	 * {@code
	 * <Parameters>
	 *   <Parameter>
	 *     <Name>null</Name>
	 *     <Value>e:/temp</Value>
	 *   </Parameter>
	 * </Parameters>
	 * }
	 * </pre>
	 * 
	 * <br/>
	 *            If the command or script does not have any parameter, pass
	 *            empty string "".
	 * @return Returns command or script's output.
	 */
	public String execute(String command, String parameters) {
		return this.execute(command, parameters, this.scriptDir);
	}
	
	/**
	 * @param snapIn
	 * Provide the snap in to be added to this powershell session. <br/>
	 * Ex. "Microsoft.Exchange.Management.PowerShell.E2010"
	 * @return
	 * It returns the snapin name if sucessful, else returns the error.
	 */
	public String addSnapIn(String snapIn) {
		return this.addSnapIn(snapIn, scriptDir);
	}

}