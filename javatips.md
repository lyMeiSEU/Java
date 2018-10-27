## Java小贴士
### String 和 其他类型相互转换
1. 其他类型转String：String s = String.valueOf( value); // 其中 value 为任意一种数字类型。
2. 字符串型转换成各种数字类型：TYPE TP=TYPE.parseTYPE(string); <br/>例：int i = Integer.parseInt( s ); long l = Long.parseLong( s ); 
 