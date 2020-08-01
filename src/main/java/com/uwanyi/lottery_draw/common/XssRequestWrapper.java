package com.uwanyi.lottery_draw.common;

/**
 * created by jasonwang
 * on 2019/9/7 15:56
 */
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XssRequestWrapper extends HttpServletRequestWrapper {
    private static List<Pattern> patterns = new ArrayList<>();

    static{
        List<String> regexList = new ArrayList<>();
        regexList.add("<(no)?script[^>]*>.*?</(no)?script>");
        regexList.add("eval\\((.*?)\\)");
        regexList.add("expression\\((.*?)\\)");
        regexList.add("(javascript:|vbscript:|view-source:)*");
        regexList.add("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>");
        regexList.add("(window\\.location|window\\.|\\.location|document\\.cookie|document\\.|alert\\(.*?\\)|window\\.open\\()*");
        regexList.add("<+\\s*\\w*\\s*(oncontrolselect|oncopy|oncut|ondataavailable|ondatasetchanged|ondatasetcomplete|ondblclick|ondeactivate|ondrag|ondragend|ondragenter|ondragleave|ondragover|ondragstart|ondrop|onerror=|onerroupdate|onfilterchange|onfinish|onfocus|onfocusin|onfocusout|onhelp|onkeydown|onkeypress|onkeyup|onlayoutcomplete|onload|onlosecapture|onmousedown|onmouseenter|onmouseleave|onmousemove|onmousout|onmouseover|onmouseup|onmousewheel|onmove|onmoveend|onmovestart|onabort|onactivate|onafterprint|onafterupdate|onbefore|onbeforeactivate|onbeforecopy|onbeforecut|onbeforedeactivate|onbeforeeditocus|onbeforepaste|onbeforeprint|onbeforeunload|onbeforeupdate|onblur|onbounce|oncellchange|onchange|onclick|oncontextmenu|onpaste|onpropertychange|onreadystatechange|onreset|onresize|onresizend|onresizestart|onrowenter|onrowexit|onrowsdelete|onrowsinserted|onscroll|onselect|onselectionchange|onselectstart|onstart|onstop|onsubmit|onunload)+\\s*=+");
        regexList.add("%|select |insert |delete |from |count\\(|drop table| update |truncate |asc \\(|mid \\(|char \\(|xp_cmdshell |exec |master |netlocalgroup administrators|net user| or | and ");
        for (String s : regexList) {
            patterns.add(Pattern.compile(s));
        }
    }

    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        // 返回值之前 先进行过滤
        return fliterXSS(super.getParameter(fliterXSS(name)));
    }

    @Override
    public String[] getParameterValues(String name) {
        // 返回值之前 先进行过滤
        String[] values = super.getParameterValues(fliterXSS(name));
        if(values != null){
            for (int i = 0; i < values.length; i++) {
                values[i] = fliterXSS(values[i]);
            }
        }
        return values;
    }

    public static String fliterXSS(String value) {

        if (null == value) {
            return value;
        }
        if (!StringUtils.isEmpty(value)) {

            Matcher matcher = null;
            for (Pattern pattern : patterns) {
                matcher = pattern.matcher(value);
                if (matcher.find()) {
                    value = matcher.replaceAll("");
                }
            }
        }
        return value;
    }
}