package util;

public class Helper {
	
	//Transforms javascript's encodeuricomponent coded String to Java's URLEncoding/Decoding format String
	public static String TransformEncoding(String param)
	{
		return param.replaceAll("\\%20", "+").replaceAll("\\!", "%21").replaceAll("\\'", "%27").replaceAll("\\(", "%28").replaceAll("\\)", "%29").replaceAll("\\~", "%7E");
	}

}
