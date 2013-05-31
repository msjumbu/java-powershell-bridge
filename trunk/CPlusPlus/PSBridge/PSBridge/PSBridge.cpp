#using <mscorlib.dll>
// This is the main DLL file.
#include <jni.h>
#include "stdafx.h"
// This is the java header created using the javah -jni command.
#include "java\com_cordys_coe_jni_PSBridge.h"

// This is the Managed C++ header that contains the call to the C#
#include "csharp\com_cordys_coe_jni_PSBridge.h"

/**
    @author Senthil kumar Murugesan
    @version 1.0 26/05/2013
*/

JNIEXPORT jstring JNICALL Java_com_cordys_coe_jni_PSBridge_execute (JNIEnv * jenv, jobject, jstring powerCmd, jstring powerParams, jstring scriptDirectory)
{
	try {
    const char *cPowerCmd;
	const char *cScriptDir;
	const char *cPowerParams;
	const char* result;

	cScriptDir = jenv->GetStringUTFChars(scriptDirectory, 0);
	PSBridge* t = PSBridge::getInstance(cScriptDir);

	cPowerCmd = jenv->GetStringUTFChars(powerCmd, 0);
	cPowerParams = jenv->GetStringUTFChars(powerParams, 0);
	String* checkStr = t->executePowershellCommand(cPowerCmd,cPowerParams);
	result = t->managed_to_ansi(checkStr);

	return jenv->NewStringUTF(result);
	 }
	 catch(Exception *e)
	 {
	 	//Console::WriteLine(e->Message);
		char* str2 = (char*)Marshal::StringToHGlobalAnsi(e->Message).ToPointer();
		return jenv->NewStringUTF(str2);
	 }

}

/*
 * Class:     com_cordys_coe_jni_PSBridge
 * Method:    addSnapIn
 * Signature: (Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT jstring JNICALL Java_com_cordys_coe_jni_PSBridge_addSnapIn
  (JNIEnv * jenv, jobject, jstring powerSnapIn, jstring scriptDirectory)
{
	try {
		const char *cPowerSnapIn;
		const char *cScriptDir;
		cScriptDir = jenv->GetStringUTFChars(scriptDirectory, 0);
		cPowerSnapIn = jenv->GetStringUTFChars(powerSnapIn, 0);

		PSBridge* t = PSBridge::getInstance(cScriptDir);
		t->powershelladdPSSnapIn(cPowerSnapIn);

		return powerSnapIn;
	}
	catch(Exception *e)
	{
		char* str2 = (char*)Marshal::StringToHGlobalAnsi(e->Message).ToPointer();
		return jenv->NewStringUTF(str2);
	}
}
