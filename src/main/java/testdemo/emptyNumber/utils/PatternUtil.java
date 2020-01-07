package testdemo.emptyNumber.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * java pattern工具类
 */
public class PatternUtil
{
	private static ThreadLocal<Map<String, Pattern>> localPattern = new ThreadLocal<Map<String, Pattern>>();
	private static ThreadLocal<Map<String, Pattern>> localPatternMultiline = new ThreadLocal<Map<String, Pattern>>();
	/**
	 * 获取编译好的pattern
	 * 
	 * @param regex
	 * @return
	 */
	public static Pattern getPattern(String regex)
	{
		Map<String, Pattern> map = localPattern.get();
		if (map == null)
		{
			map = new ConcurrentHashMap<String, Pattern>();
			localPattern.set(map);
		}

		Pattern p = map.get(regex);
		if (p == null)
		{
			p = Pattern.compile(regex);
			map.put(regex, p);
		}
		return p;
	}
	
	public static Pattern getPattern(boolean multiline, String regex)
	{
		Map<String, Pattern> map = localPatternMultiline.get();
		if (map == null)
		{
			map = new ConcurrentHashMap<String, Pattern>();
			localPatternMultiline.set(map);
		}

		Pattern p = map.get(regex);
		if (p == null)
		{
			p = Pattern.compile(regex, Pattern.MULTILINE|Pattern.DOTALL);
			map.put(regex, p);
		}
		return p;
	}
}