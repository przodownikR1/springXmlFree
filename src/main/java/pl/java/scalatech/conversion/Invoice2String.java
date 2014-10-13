package pl.java.scalatech.conversion;

import org.springframework.core.convert.converter.Converter;

import pl.java.scalatech.domain.Invoice;

public class Invoice2String implements Converter<Invoice, String> {

    @Override
    public String convert(Invoice source) {

        return source.getName();
    }
        
}
