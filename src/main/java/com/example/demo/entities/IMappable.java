package com.example.demo.entities;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface IMappable {

    default void fromMap(Map<String, String> map) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        for (Method m : this.getClass().getMethods()) {
            if (m.getName().startsWith("set") && m.getParameterCount() == 1) {
                String nomeProprieta = m.getName().substring(3);
                nomeProprieta = Character.toLowerCase(nomeProprieta.charAt(0)) + nomeProprieta.substring(1);
                String nomeProprietaLower = nomeProprieta.toLowerCase();
                if (map.containsKey(nomeProprietaLower)) {
                    String valoreAssociato = map.get(nomeProprietaLower);
                    if (valoreAssociato == null) {
                        continue;
                    }
                    String tipoParametro = m.getParameters()[0].getType().getSimpleName().toLowerCase();
                    try {
                        switch (tipoParametro) {
                            case "string":
                                m.invoke(this, valoreAssociato);
                                break;
                            case "int":
                                m.invoke(this, Integer.parseInt(valoreAssociato));
                                break;
                            case "double":
                                m.invoke(this, Double.parseDouble(valoreAssociato));
                                break;
                            case "localdate":
                                m.invoke(this, LocalDate.parse(valoreAssociato));
                                break;
                            case "boolean":
                                m.invoke(this, (valoreAssociato.equals("1") ? true : false));
                                break;
                            case "long":
                                m.invoke(this, Long.parseLong(valoreAssociato));
                                break;
                            case "localtime":
                                m.invoke(this, LocalTime.parse(valoreAssociato));
                                break;
                        }
                    } catch (Exception ex) {
                        logger.error("Errore durante il mapping della proprietà " + nomeProprieta + " con valore "
                                + valoreAssociato + " di tipo " + tipoParametro);
                    }

                }
            }

        }

    }

    default Map<String, String> toMap() {
        Map<String, String> result = new LinkedHashMap<>();
        for (Method m : this.getClass().getMethods()) {
            if ((m.getName().startsWith("get") || m.getName().startsWith("is"))
                    &&
                    !m.getName().equalsIgnoreCase("getClass") && m.getParameterCount() == 0) {

                int partenza = m.getName().startsWith("get") ? 3 : 2;
                String nomeProprieta = m.getName().substring(partenza);
                nomeProprieta = Character.toLowerCase(nomeProprieta.charAt(0)) + nomeProprieta.substring(1);
                try {
                    String valore = null;
                    if (partenza == 2) {
                        valore = m.invoke(this).toString().equalsIgnoreCase("true") ? "1" : "0";
                    } else if (partenza == 3) {
                        valore = String.valueOf(m.invoke(this));
                    }
                    result.put(nomeProprieta.toLowerCase(), valore);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return result;
    }

}
