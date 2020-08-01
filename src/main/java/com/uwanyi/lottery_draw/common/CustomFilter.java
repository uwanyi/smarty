package com.uwanyi.lottery_draw.common;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * 自定义过滤器
 * created by jasonwang
 * on 2019/7/25 14:44
 */
public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //说明：如果有特殊请求时，可以在此处获取url,用if判断url.equal()是否过XssRequestWrapper过滤器来装饰request。
        // 例如提交富文本时，富文本的内容有可能允许select 等关键字的存在，过滤规则可以在对应的请求路径的action层做
        HttpServletRequest request = (HttpServletRequest)req;
        request = new XssRequestWrapper(request);

        HttpServletResponse response = (HttpServletResponse) res;
        String requestStr = getRequestString(request);

        if ("bingo".equals(guolv2(requestStr))
                || "bingo".equals(guolv2(request.getRequestURL().toString()))) {
            System.out.println("访问地址发现非法字符，已拦截======其非法地址为："+guolv2(request.getRequestURL().toString()));
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        // 主机ip和端口 或 域名和端口 防止host头攻击
        /*String myhosts = request.getHeader("host");
        String[] whiteAddr = S.whiteAddress.split(",");
        boolean flag = false;
        int len = whiteAddr.length;
        for(int i = 0; i < len; i++){
            if(myhosts.equals(whiteAddr[i])){
                flag = true;
                break;
            }
        }
        if(!flag){
            //如果不在白名单里面，则跳转到首页
            response.sendRedirect(request.getContextPath() + "/index.action");  //或者response.setStatus(403);
            return;
        }*/
        // add by wangsk 过滤请求特殊字符，扫描跨站式漏洞
        Map parameters = request.getParameterMap();
        if (parameters != null && parameters.size() > 0) {
            for (Iterator iter = parameters.keySet().iterator(); iter.hasNext();) {
                String key = (String) iter.next();
                String[] values = (String[]) parameters.get(key);
                for (int i = 0; i < values.length; i++) {
                    values[i] = guolv(values[i]);
                    //System.out.println(values[i]);
                }
            }
        }

        chain.doFilter(request, res);
    }
    private static String guolv(String a) {
        a = a.replaceAll("%22", "");
        a = a.replaceAll("%27", "");
        a = a.replaceAll("%3E", "");
        a = a.replaceAll("%3e", "");
        a = a.replaceAll("%3C", "");
        a = a.replaceAll("%3c", "");
        a = a.replaceAll("<", "");
        a = a.replaceAll(">", "");
        a = a.replaceAll("\"", "");
        a = a.replaceAll("'", "");
        a = a.replaceAll("\\+", "");
        a = a.replaceAll("\\(", "");
        a = a.replaceAll("\\)", "");
        a = a.replaceAll(" and ", "");
        a = a.replaceAll(" or ", "");
        a = a.replaceAll(" 1=1 ", "");
        return a;
    }

    private String getRequestString(HttpServletRequest req) {
        String requestPath = req.getServletPath().toString();
        String queryString = req.getQueryString();
        if (queryString != null)
            return requestPath + "?" + queryString;
        else
            return requestPath;
    }

    private String guolv2(String a) {
        if (!StringUtils.isEmpty(a)) {
            if (a.contains("%22") || a.contains("%3E") || a.contains("%3e")
                    || a.contains("%3C") || a.contains("%3c")
                    || a.contains("<") || a.contains(">") || a.contains("\"")
                    || a.contains("'") || a.contains("+") || /*
                                                             * a.contains("%27")
                                                             * ||
                                                             */
                    a.contains(" and ") || a.contains(" or ")
                    || a.contains("1=1") || a.contains("(") || a.contains(")")) {
                return "bingo";
            }
        }
        return a;
    }
}
