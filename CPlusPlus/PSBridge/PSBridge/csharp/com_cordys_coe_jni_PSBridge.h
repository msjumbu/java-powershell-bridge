#using <mscorlib.dll>
#include <vcclr.h> // PtrToStringChars()
#include <stdio.h>
#include <string.h>
#using "csharp\PS.netmodule"

using namespace System;
using System::String;
using System::Collections::Hashtable;
using namespace System::Runtime::InteropServices;

/**
    @author Senthil kumar Murugesan
    @version 1.0 26/05/2013
*/


// This code wraps the C# class using Managed C++
public __gc class PSBridge
{
private:
	PSBridge() {};
    static Hashtable *PSEs = new Hashtable();
	Powershell::PS __gc *t;

	PSBridge(const char* cScriptDir) {
		String __gc *scriptDir = new String(cScriptDir);
		t = Powershell::PS::getInstance(scriptDir);
	}


public:

	static PSBridge* getInstance(const char* str1) 
	{
		String __gc *cscriptDir = new String(str1);
		if (PSEs->ContainsKey(cscriptDir))
			return (PSBridge *)PSEs->get_Item(cscriptDir);
		else
		{
			PSBridge* t = new PSBridge(str1);
			PSEs->Add(cscriptDir, t);
			return t;
		}
	}

	String* executePowershellCommand(const char* str,const char* str1) {
		String __gc *command = new String(str);
		String __gc *params = new String(str1);
 
		return t->execute(command,params);
  	}

	void powershelladdPSSnapIn(const char* str) {
		String __gc *spanIn = new String(str);

		return t->addPSSnapIn(spanIn);
  	}

	const char* managed_to_ansi(String __gc *str)
	{
	    char* str2 = (char*)Marshal::StringToHGlobalAnsi(str).ToPointer();
		return str2;
	}
};



