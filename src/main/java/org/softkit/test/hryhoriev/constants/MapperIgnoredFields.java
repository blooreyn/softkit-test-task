package org.softkit.test.hryhoriev.constants;

import java.util.Collections;
import java.util.List;

public final class MapperIgnoredFields {

    public static final List<String> QUOTE_DTO = Collections.singletonList(TableFields.ID);

    private MapperIgnoredFields() {
        throw new UnsupportedOperationException();
    }
}
