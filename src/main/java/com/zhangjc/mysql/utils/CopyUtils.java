package com.zhangjc.mysql.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class CopyUtils {

    private CopyUtils() {
    }

    public static <S, D> D copyPropertys(S s, D d) {
        if (s == null || d == null) {
            return d;
        }
        Field[] sfields = s.getClass().getDeclaredFields();
        Field[] dfields = d.getClass().getDeclaredFields();
        Class scls = s.getClass();
        Class dcls = d.getClass();
        try {
            for (Field sfield : sfields) {
                String sName = sfield.getName();
                Class sType = sfield.getType();
                String sfieldName = sName.substring(0, 1).toUpperCase() + sName.substring(1);
                Method sGetMethod = scls.getMethod("get" + sfieldName);
                Object value = sGetMethod.invoke(s);
                for (Field dfield : dfields) {
                    String dName = dfield.getName();
                    Class dType = dfield.getType();
                    if (dName.equals(sName) && value != null) {
                        Method dSetMethod = dcls.getMethod("set" + sfieldName, dType);
                        String st = sType.toString();
                        String dt = dType.toString();
                        if (st.equals(dt)) {
                            dSetMethod.invoke(d, value);
                            continue;
                        }
                        if (st.contains("Date") && dt.contains("String")) {
                            String sd = DateUtil.dateCoverterToStr((Date) value);
                            dSetMethod.invoke(d, sd);
                        }
                        if (st.contains("String") && dt.contains("Date")) {
                            Date dd = DateUtil.strCoverterToDate(value.toString());
                            dSetMethod.invoke(d, dd);
                        }
                        if (st.contains("Long") && dt.contains("String")) {
                            String sd = value.toString();
                            dSetMethod.invoke(d, sd);
                        }
                        if (st.contains("String") && dt.contains("Long")) {
                            Long dd = Long.parseLong(value.toString());
                            dSetMethod.invoke(d, dd);
                        }
                    }
                }
            }
        } catch (SecurityException | NoSuchMethodException | IllegalAccessException
                | InvocationTargetException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return d;
    }
}
