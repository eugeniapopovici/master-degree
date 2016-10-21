package md.usarb.cinema.utils;

import flexjson.transformer.AbstractTransformer;

/**
 * This class is meant to exclude null objects while serialization process.
 *
 * @author Natalia Balan
 */
public class ExcludeTransformer extends AbstractTransformer {

    @Override
    public Boolean isInline() {
        return true;
    }

    @Override
    public void transform(Object object) {

    }
}
