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

import org.w3c.dom.Document;

import java.io.File;

/**
 * Created by liumeng on 1/8/16.
 */
public class LayoutFileNameDetector extends ResourceXmlDetector {

    public static final Issue ISSUE = Issue.create("LayoutFileName",
            "LayoutFileName",
            "check your layout file name",
            Category.TYPOGRAPHY,
            5,
            Severity.ERROR,
            new Implementation(LayoutFileNameDetector.class,
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
    public void visitDocument(@NonNull XmlContext context, @NonNull Document document) {
        context.report(ISSUE,
                null,
                "layout file name:" + context.file.getName());
    }
}
