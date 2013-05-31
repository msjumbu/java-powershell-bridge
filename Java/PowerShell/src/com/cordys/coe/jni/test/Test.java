package com.cordys.coe.jni.test;

import com.cordys.coe.jni.PSBridge;

/**
 * This class demonstrates the usage of PSBridge.
 * 
 * @author Senthil kumar Murugesan
 * @version 1.0
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PSBridge psb = new PSBridge("e:/psscripts");
		long millis = System.currentTimeMillis();
		String ret = psb
				.addSnapIn("Microsoft.Exchange.Management.PowerShell.E2010");
		System.out.println(ret);
		System.out.println((System.currentTimeMillis() - millis) * 0.001);
		millis = System.currentTimeMillis();
		
		ret = psb
				.execute(
						"./CreateSharedMailbox1.ps1",
						"<Parameters><Parameter><Name>DistinguishedName</Name><Value>CN=nologon,CN=Users,DC=xcorcap,DC=tst</Value></Parameter></Parameters>");
		System.out.println(ret);
		System.out.println((System.currentTimeMillis() - millis) * 0.001);
		millis = System.currentTimeMillis();
		
		ret = psb
				.execute(
						"./CreateSharedMailbox1.ps1",
						"<Parameters><Parameter><Name>DistinguishedName</Name><Value>CN=nologon,CN=Users,DC=xcorcap,DC=tst</Value></Parameter></Parameters>");
		System.out.println(ret);
		System.out.println((System.currentTimeMillis() - millis) * 0.001);
		millis = System.currentTimeMillis();
		
		ret = psb
				.execute(
						"./CreateSharedMailbox1.ps1",
						"<Parameters><Parameter><Name>DistinguishedName</Name><Value>CN=nologon,CN=Users,DC=xcorcap,DC=tst</Value></Parameter></Parameters>");
		System.out.println(ret);
		System.out.println((System.currentTimeMillis() - millis) * 0.001);
		millis = System.currentTimeMillis();
		
		ret = psb
				.execute(
						"get-childitem",
						"<Parameters><Parameter><Name>null</Name><Value>e:/oly</Value></Parameter></Parameters>");

		System.out.println(ret);
		System.out.println((System.currentTimeMillis() - millis) * 0.001);
		millis = System.currentTimeMillis();
	}

}
