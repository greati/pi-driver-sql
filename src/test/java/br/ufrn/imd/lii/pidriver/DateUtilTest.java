package br.ufrn.imd.lii.pidriver;

import br.ufrn.imd.lii.common.utils.DateUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilTest {

    @Test
    public void addPeriodInDate() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date = new Date("01/01/01 00:00:00");
        Date genDate = DateUtil.addSeconds(date, 1);
        System.out.printf(fmt.format(genDate));
    }
    @Test
    public void subPeriodInDate() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date = new Date("01/01/01 00:00:01");
        Date genDate = DateUtil.subtractSeconds(date, 1);
        System.out.printf(fmt.format(genDate));
    }


}
