package br.com.gogar.sistema.api.convert;

import br.com.gogar.sistema.api.domain.dto.EquipmentDTO;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ConvertMap {
    public static Map<String, Object> convertDTOToMap(Object dto) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = dto.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(dto);
            map.put(field.getName(), value);
        }

        return map;
    }


}
