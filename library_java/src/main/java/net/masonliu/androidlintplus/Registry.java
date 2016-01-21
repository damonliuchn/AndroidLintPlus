package net.masonliu.androidlintplus;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.Arrays;
import java.util.List;

/**
 * Created by liumeng on 1/8/16.
 */
public final class Registry extends IssueRegistry {
    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(ViewIdDetector.ISSUE, LayoutFileNameDetector.ISSUE, JavaChineseStringDetector.ISSUE);
    }
}
