package indiv.peter.serviceedu.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PeterRays
 * @date 2021/9/20
 */
public class ExcelTest {
    @Test
    public void writeTest() {
        String fileName = "D:\\Temp\\2021\\write.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("学生列表Sheet").doWrite(data());
    }

    //循环设置要添加的数据，最终封装到list集合中
    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("李四" + i);
            list.add(data);
        }
        return list;
    }

    @Test
    public void readTest() {
        String fileName = "D:\\Temp\\2021\\write.xlsx";
        EasyExcel.read(fileName, DemoData.class, new ExcelListener()).sheet().doRead();
    }
}
