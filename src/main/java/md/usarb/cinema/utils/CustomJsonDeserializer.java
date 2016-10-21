package md.usarb.cinema.utils;

import flexjson.JSONDeserializer;
import flexjson.factories.BooleanAsStringObjectFactory;
import flexjson.transformer.DateTransformer;
import md.usarb.cinema.model.SearchFilter;

import java.util.Date;


/**
 * This class is meant to transform {@link flexjson.JSON} to {@link Object}
 *
 * @author Natalia Balan
 */
public class CustomJsonDeserializer {

    private static final DateTransformer DATE_TRANSFORMER = new DateTransformer("MM/dd/yyyy");

    /**
     * This method transforms @link flexjson.JSON} to {@link SearchFilter}
     *
     * @param json the json string
     * @return the {@link SearchFilter} object
     */
    public static SearchFilter jsonDeserializer(String json){
        return new JSONDeserializer<SearchFilter>().use(Boolean.class, new BooleanAsStringObjectFactory("true", "false"))
                .use(Date.class, DATE_TRANSFORMER).deserialize(json, SearchFilter.class);
    }
}
