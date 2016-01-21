package net.masonliu.androidlintplus;

import com.android.annotations.NonNull;
import com.android.resources.ResourceFolderType;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.LintUtils;
import com.android.tools.lint.detector.api.ResourceXmlDetector;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.XmlContext;

import org.w3c.dom.Attr;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by liumeng on 1/8/16.
 */
public class ViewIdDetector extends ResourceXmlDetector {

    public static final Issue ISSUE = Issue.create("ViewId",
            "ViewId",
            "check your view id in layout file",
            Category.TYPOGRAPHY,
            5,
            Severity.ERROR,
            new Implementation(ViewIdDetector.class,
                    Scope.RESOURCE_FILE_SCOPE));

    @Override
    public boolean appliesTo(@NonNull Context context,
                             @NonNull File file) {
        return LintUtils.isXmlFile(file);
    }

    @Override
    public boolean appliesTo(ResourceFolderType folderType) {
        return ResourceFolderType.LAYOUT == folderType;
    }

    @Override
    public Collection<String> getApplicableAttributes() {
        return Collections.singletonList("id");
    }

    @Override
    public void visitAttribute(@NonNull XmlContext context, @NonNull Attr attribute) {
        super.visitAttribute(context, attribute);
        context.report(ISSUE,
                attribute,
                context.getLocation(attribute),
                "");
    }
}
