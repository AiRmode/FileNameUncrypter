package p1;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by alshevchuk on 30.01.2016.
 */
public class FinePrinter {

    public static String createHTMLLikeFile(Map<String, String> map) {
        map = new TreeMap<>(map);
        StringBuffer sb = new StringBuffer("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=Windows-1251\">");
        for (String key : map.keySet()) {
            sb.append(key).append("</br><b>").append(map.get(key)).append("</b></br></br>");
        }
        return sb.toString();
    }
}
