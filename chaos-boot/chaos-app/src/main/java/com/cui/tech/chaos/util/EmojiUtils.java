package com.cui.tech.chaos.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 特殊字符表情过滤工具类，转换成？号
 *
 * @author xiaoshiyilang
 * @version 1.0
 * @date 2018/10/25
 */
public class EmojiUtils {

    public static String filterEmoji(String source) {
        if (source == null) {
            return source;
        }
        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher emojiMatcher = emoji.matcher(source);
        if (emojiMatcher.find()) {
            source = emojiMatcher.replaceAll("?");
            return source;
        }
        return source;
    }

}
