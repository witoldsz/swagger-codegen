package io.swagger.codegen.languages;

import io.swagger.models.properties.FileProperty;
import io.swagger.models.properties.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import io.swagger.codegen.SupportingFile;

public class TypeScriptNodeCodegen extends AbstractTypeScriptClientCodegen {
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeScriptNodeCodegen.class);
    private static final SimpleDateFormat SNAPSHOT_SUFFIX_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");

    protected String npmName = null;
    protected String npmVersion = "1.0.0";
    protected String npmRepository = null;

    public TypeScriptNodeCodegen() {
        super();

        typeMapping.put("file", "Buffer");

        // clear import mapping (from default generator) as TS does not use it
        // at the moment
        importMapping.clear();

        outputFolder = "generated-code/typescript-node";
        embeddedTemplateDir = templateDir = "typescript-node";
    }


    @Override
    public void processOpts() {
        super.processOpts();
        supportingFiles.add(new SupportingFile("api.mustache", null, "api.ts"));
    }

    @Override
    public String getName() {
        return "typescript";
    }

    @Override
    public String getHelp() {
        return "Generates a TypeScript nodejs server library.";
    }

    @Override
    public boolean isDataTypeFile(final String dataType) {
        return dataType != null && dataType.equals("Buffer");
    }

    @Override
    public String getTypeDeclaration(Property p) {
        if (p instanceof FileProperty) {
            return "Buffer";
        }
        return super.getTypeDeclaration(p);
    }

}
