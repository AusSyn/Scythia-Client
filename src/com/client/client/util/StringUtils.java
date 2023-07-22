package com.client.client.util;

public class StringUtils
{
	public static String numToShort (Long x) 
	{
		long thousand = (1_000L);
		long million = (1_000_000L);
		long billion = (1_000_000_000L);
		long trillion = (1_000_000_000_000L);
		long quadrillion = (1_000_000_000_000_000L);
		long quintillion = (1_000_000_000_000_000_000L);
		String converted = null;
		
		if (x < thousand) { converted = x.toString(); }
		else if (x < million) { converted = (x*100/thousand/100 + "K"); 
		}
		else if (x < billion) { converted = (x*100/million/100 + "M"); 
		}
		else if (x < trillion) { converted = (x*100/billion/100 + "B"); 
		}
		else if (x < quadrillion) { converted = (x*100/trillion/100 + "T"); 
		}
		else if (x < quintillion) { converted = (x*100/quadrillion/100 +"Q"); 
		}
		else { converted = (x*100/quintillion/100 +"QT"); 
		}
		return converted;
	}
}
