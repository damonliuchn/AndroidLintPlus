package net.masonliu.androidlintplus;

import com.android.annotations.NonNull;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.ast.AlternateConstructorInvocation;
import lombok.ast.Annotation;
import lombok.ast.AnnotationDeclaration;
import lombok.ast.AnnotationElement;
import lombok.ast.AnnotationMethodDeclaration;
import lombok.ast.AnnotationValueArray;
import lombok.ast.ArrayAccess;
import lombok.ast.ArrayCreation;
import lombok.ast.ArrayDimension;
import lombok.ast.ArrayInitializer;
import lombok.ast.Assert;
import lombok.ast.AstVisitor;
import lombok.ast.BinaryExpression;
import lombok.ast.Block;
import lombok.ast.BooleanLiteral;
import lombok.ast.Break;
import lombok.ast.Case;
import lombok.ast.Cast;
import lombok.ast.Catch;
import lombok.ast.CharLiteral;
import lombok.ast.ClassDeclaration;
import lombok.ast.ClassLiteral;
import lombok.ast.Comment;
import lombok.ast.CompilationUnit;
import lombok.ast.ConstructorDeclaration;
import lombok.ast.ConstructorInvocation;
import lombok.ast.Continue;
import lombok.ast.Default;
import lombok.ast.DoWhile;
import lombok.ast.EmptyDeclaration;
import lombok.ast.EmptyStatement;
import lombok.ast.EnumConstant;
import lombok.ast.EnumDeclaration;
import lombok.ast.EnumTypeBody;
import lombok.ast.ExpressionStatement;
import lombok.ast.FloatingPointLiteral;
import lombok.ast.For;
import lombok.ast.ForEach;
import lombok.ast.Identifier;
import lombok.ast.If;
import lombok.ast.ImportDeclaration;
import lombok.ast.InlineIfExpression;
import lombok.ast.InstanceInitializer;
import lombok.ast.InstanceOf;
import lombok.ast.IntegralLiteral;
import lombok.ast.InterfaceDeclaration;
import lombok.ast.KeywordModifier;
import lombok.ast.LabelledStatement;
import lombok.ast.MethodDeclaration;
import lombok.ast.MethodInvocation;
import lombok.ast.Modifiers;
import lombok.ast.Node;
import lombok.ast.NormalTypeBody;
import lombok.ast.NullLiteral;
import lombok.ast.PackageDeclaration;
import lombok.ast.Return;
import lombok.ast.Select;
import lombok.ast.StaticInitializer;
import lombok.ast.StringLiteral;
import lombok.ast.Super;
import lombok.ast.SuperConstructorInvocation;
import lombok.ast.Switch;
import lombok.ast.Synchronized;
import lombok.ast.This;
import lombok.ast.Throw;
import lombok.ast.Try;
import lombok.ast.TypeReference;
import lombok.ast.TypeReferencePart;
import lombok.ast.TypeVariable;
import lombok.ast.UnaryExpression;
import lombok.ast.VariableDeclaration;
import lombok.ast.VariableDefinition;
import lombok.ast.VariableDefinitionEntry;
import lombok.ast.VariableReference;
import lombok.ast.While;

/**
 * Created by liumeng on 1/8/16.
 */
public class JavaChineseStringDetector extends Detector implements Detector.JavaScanner {

    public static final Issue ISSUE = Issue.create("JavaChineseString",
            "JavaChineseString",
            "check chinese string in java file",
            Category.PERFORMANCE,
            5,
            Severity.ERROR,
            new Implementation(JavaChineseStringDetector.class,
                    Scope.JAVA_FILE_SCOPE));

    public AstVisitor createJavaVisitor(@NonNull JavaContext context) {
        return new AstVisitor() {
            @Override
            public boolean visitTypeReference(TypeReference typeReference) {
                return false;
            }

            @Override
            public boolean visitTypeReferencePart(TypeReferencePart typeReferencePart) {
                return false;
            }

            @Override
            public boolean visitVariableReference(VariableReference variableReference) {
                return false;
            }

            @Override
            public boolean visitIdentifier(Identifier identifier) {
                return false;
            }

            @Override
            public boolean visitIntegralLiteral(IntegralLiteral integralLiteral) {
                return false;
            }

            @Override
            public boolean visitFloatingPointLiteral(FloatingPointLiteral floatingPointLiteral) {
                return false;
            }

            @Override
            public boolean visitBooleanLiteral(BooleanLiteral booleanLiteral) {
                return false;
            }

            @Override
            public boolean visitCharLiteral(CharLiteral charLiteral) {
                return false;
            }

            @Override
            public boolean visitStringLiteral(StringLiteral stringLiteral) {
                String patternStr = "[\\u4e00-\\u9fa5]";
                Pattern pattern = Pattern.compile(patternStr);
                Matcher matcher = pattern.matcher(stringLiteral.astValue());
                if (matcher.find()) {
                    context.report(
                            ISSUE,
                            stringLiteral, context.getLocation(stringLiteral),
                            "chinese string:" + stringLiteral.astValue()
                    );
                }
                return false;
            }

            @Override
            public boolean visitNullLiteral(NullLiteral nullLiteral) {
                return false;
            }

            @Override
            public boolean visitBinaryExpression(BinaryExpression binaryExpression) {
                return false;
            }

            @Override
            public boolean visitUnaryExpression(UnaryExpression unaryExpression) {
                return false;
            }

            @Override
            public boolean visitInlineIfExpression(InlineIfExpression inlineIfExpression) {
                return false;
            }

            @Override
            public boolean visitCast(Cast cast) {
                return false;
            }

            @Override
            public boolean visitInstanceOf(InstanceOf instanceOf) {
                return false;
            }

            @Override
            public boolean visitConstructorInvocation(ConstructorInvocation constructorInvocation) {
                return false;
            }

            @Override
            public boolean visitMethodInvocation(MethodInvocation methodInvocation) {
                return false;
            }

            @Override
            public boolean visitSelect(Select select) {
                return false;
            }

            @Override
            public boolean visitArrayAccess(ArrayAccess arrayAccess) {
                return false;
            }

            @Override
            public boolean visitArrayCreation(ArrayCreation arrayCreation) {
                return false;
            }

            @Override
            public boolean visitArrayInitializer(ArrayInitializer arrayInitializer) {
                return false;
            }

            @Override
            public boolean visitAnnotationValueArray(AnnotationValueArray annotationValueArray) {
                return false;
            }

            @Override
            public boolean visitArrayDimension(ArrayDimension arrayDimension) {
                return false;
            }

            @Override
            public boolean visitClassLiteral(ClassLiteral classLiteral) {
                return false;
            }

            @Override
            public boolean visitSuper(Super aSuper) {
                return false;
            }

            @Override
            public boolean visitThis(This aThis) {
                return false;
            }

            @Override
            public boolean visitLabelledStatement(LabelledStatement labelledStatement) {
                return false;
            }

            @Override
            public boolean visitExpressionStatement(ExpressionStatement expressionStatement) {
                return false;
            }

            @Override
            public boolean visitIf(If anIf) {
                return false;
            }

            @Override
            public boolean visitFor(For aFor) {
                return false;
            }

            @Override
            public boolean visitForEach(ForEach forEach) {
                return false;
            }

            @Override
            public boolean visitTry(Try aTry) {
                return false;
            }

            @Override
            public boolean visitCatch(Catch aCatch) {
                return false;
            }

            @Override
            public boolean visitWhile(While aWhile) {
                return false;
            }

            @Override
            public boolean visitDoWhile(DoWhile doWhile) {
                return false;
            }

            @Override
            public boolean visitSynchronized(Synchronized aSynchronized) {
                return false;
            }

            @Override
            public boolean visitBlock(Block block) {
                return false;
            }

            @Override
            public boolean visitAssert(Assert anAssert) {
                return false;
            }

            @Override
            public boolean visitEmptyStatement(EmptyStatement emptyStatement) {
                return false;
            }

            @Override
            public boolean visitSwitch(Switch aSwitch) {
                return false;
            }

            @Override
            public boolean visitCase(Case aCase) {
                return false;
            }

            @Override
            public boolean visitDefault(Default aDefault) {
                return false;
            }

            @Override
            public boolean visitBreak(Break aBreak) {
                return false;
            }

            @Override
            public boolean visitContinue(Continue aContinue) {
                return false;
            }

            @Override
            public boolean visitReturn(Return aReturn) {
                return false;
            }

            @Override
            public boolean visitThrow(Throw aThrow) {
                return false;
            }

            @Override
            public boolean visitVariableDeclaration(VariableDeclaration variableDeclaration) {
                return false;
            }

            @Override
            public boolean visitVariableDefinition(VariableDefinition variableDefinition) {
                return false;
            }

            @Override
            public boolean visitVariableDefinitionEntry(VariableDefinitionEntry variableDefinitionEntry) {
                return false;
            }

            @Override
            public boolean visitTypeVariable(TypeVariable typeVariable) {
                return false;
            }

            @Override
            public boolean visitKeywordModifier(KeywordModifier keywordModifier) {
                return false;
            }

            @Override
            public boolean visitModifiers(Modifiers modifiers) {
                return false;
            }

            @Override
            public boolean visitAnnotation(Annotation annotation) {
                return false;
            }

            @Override
            public boolean visitAnnotationElement(AnnotationElement annotationElement) {
                return false;
            }

            @Override
            public boolean visitNormalTypeBody(NormalTypeBody normalTypeBody) {
                return false;
            }

            @Override
            public boolean visitEnumTypeBody(EnumTypeBody enumTypeBody) {
                return false;
            }

            @Override
            public boolean visitEmptyDeclaration(EmptyDeclaration emptyDeclaration) {
                return false;
            }

            @Override
            public boolean visitMethodDeclaration(MethodDeclaration methodDeclaration) {
                return false;
            }

            @Override
            public boolean visitConstructorDeclaration(ConstructorDeclaration constructorDeclaration) {
                return false;
            }

            @Override
            public boolean visitSuperConstructorInvocation(SuperConstructorInvocation superConstructorInvocation) {
                return false;
            }

            @Override
            public boolean visitAlternateConstructorInvocation(AlternateConstructorInvocation alternateConstructorInvocation) {
                return false;
            }

            @Override
            public boolean visitInstanceInitializer(InstanceInitializer instanceInitializer) {
                return false;
            }

            @Override
            public boolean visitStaticInitializer(StaticInitializer staticInitializer) {
                return false;
            }

            @Override
            public boolean visitClassDeclaration(ClassDeclaration classDeclaration) {
                return false;
            }

            @Override
            public boolean visitInterfaceDeclaration(InterfaceDeclaration interfaceDeclaration) {
                return false;
            }

            @Override
            public boolean visitEnumDeclaration(EnumDeclaration enumDeclaration) {
                return false;
            }

            @Override
            public boolean visitEnumConstant(EnumConstant enumConstant) {
                return false;
            }

            @Override
            public boolean visitAnnotationDeclaration(AnnotationDeclaration annotationDeclaration) {
                return false;
            }

            @Override
            public boolean visitAnnotationMethodDeclaration(AnnotationMethodDeclaration annotationMethodDeclaration) {
                return false;
            }

            @Override
            public boolean visitCompilationUnit(CompilationUnit compilationUnit) {
                return false;
            }

            @Override
            public boolean visitPackageDeclaration(PackageDeclaration packageDeclaration) {
                return false;
            }

            @Override
            public boolean visitImportDeclaration(ImportDeclaration importDeclaration) {
                return false;
            }

            @Override
            public boolean visitParseArtefact(Node node) {
                return false;
            }

            @Override
            public boolean visitComment(Comment comment) {
                return false;
            }

            @Override
            public void endVisit(Node node) {

            }

            @Override
            public void afterVisitTypeReference(TypeReference typeReference) {

            }

            @Override
            public void afterVisitTypeReferencePart(TypeReferencePart typeReferencePart) {

            }

            @Override
            public void afterVisitVariableReference(VariableReference variableReference) {

            }

            @Override
            public void afterVisitIdentifier(Identifier identifier) {

            }

            @Override
            public void afterVisitIntegralLiteral(IntegralLiteral integralLiteral) {

            }

            @Override
            public void afterVisitFloatingPointLiteral(FloatingPointLiteral floatingPointLiteral) {

            }

            @Override
            public void afterVisitBooleanLiteral(BooleanLiteral booleanLiteral) {

            }

            @Override
            public void afterVisitCharLiteral(CharLiteral charLiteral) {

            }

            @Override
            public void afterVisitStringLiteral(StringLiteral stringLiteral) {
            }

            @Override
            public void afterVisitNullLiteral(NullLiteral nullLiteral) {

            }

            @Override
            public void afterVisitBinaryExpression(BinaryExpression binaryExpression) {

            }

            @Override
            public void afterVisitUnaryExpression(UnaryExpression unaryExpression) {

            }

            @Override
            public void afterVisitInlineIfExpression(InlineIfExpression inlineIfExpression) {

            }

            @Override
            public void afterVisitCast(Cast cast) {

            }

            @Override
            public void afterVisitInstanceOf(InstanceOf instanceOf) {

            }

            @Override
            public void afterVisitConstructorInvocation(ConstructorInvocation constructorInvocation) {

            }

            @Override
            public void afterVisitMethodInvocation(MethodInvocation methodInvocation) {

            }

            @Override
            public void afterVisitSelect(Select select) {

            }

            @Override
            public void afterVisitArrayAccess(ArrayAccess arrayAccess) {

            }

            @Override
            public void afterVisitArrayCreation(ArrayCreation arrayCreation) {

            }

            @Override
            public void afterVisitArrayInitializer(ArrayInitializer arrayInitializer) {

            }

            @Override
            public void afterVisitAnnotationValueArray(AnnotationValueArray annotationValueArray) {

            }

            @Override
            public void afterVisitArrayDimension(ArrayDimension arrayDimension) {

            }

            @Override
            public void afterVisitClassLiteral(ClassLiteral classLiteral) {

            }

            @Override
            public void afterVisitSuper(Super aSuper) {

            }

            @Override
            public void afterVisitThis(This aThis) {

            }

            @Override
            public void afterVisitLabelledStatement(LabelledStatement labelledStatement) {

            }

            @Override
            public void afterVisitExpressionStatement(ExpressionStatement expressionStatement) {

            }

            @Override
            public void afterVisitIf(If anIf) {

            }

            @Override
            public void afterVisitFor(For aFor) {

            }

            @Override
            public void afterVisitForEach(ForEach forEach) {

            }

            @Override
            public void afterVisitTry(Try aTry) {

            }

            @Override
            public void afterVisitCatch(Catch aCatch) {

            }

            @Override
            public void afterVisitWhile(While aWhile) {

            }

            @Override
            public void afterVisitDoWhile(DoWhile doWhile) {

            }

            @Override
            public void afterVisitSynchronized(Synchronized aSynchronized) {

            }

            @Override
            public void afterVisitBlock(Block block) {

            }

            @Override
            public void afterVisitAssert(Assert anAssert) {

            }

            @Override
            public void afterVisitEmptyStatement(EmptyStatement emptyStatement) {

            }

            @Override
            public void afterVisitSwitch(Switch aSwitch) {

            }

            @Override
            public void afterVisitCase(Case aCase) {

            }

            @Override
            public void afterVisitDefault(Default aDefault) {

            }

            @Override
            public void afterVisitBreak(Break aBreak) {

            }

            @Override
            public void afterVisitContinue(Continue aContinue) {

            }

            @Override
            public void afterVisitReturn(Return aReturn) {

            }

            @Override
            public void afterVisitThrow(Throw aThrow) {

            }

            @Override
            public void afterVisitVariableDeclaration(VariableDeclaration variableDeclaration) {

            }

            @Override
            public void afterVisitVariableDefinition(VariableDefinition variableDefinition) {

            }

            @Override
            public void afterVisitVariableDefinitionEntry(VariableDefinitionEntry variableDefinitionEntry) {

            }

            @Override
            public void afterVisitTypeVariable(TypeVariable typeVariable) {

            }

            @Override
            public void afterVisitKeywordModifier(KeywordModifier keywordModifier) {

            }

            @Override
            public void afterVisitModifiers(Modifiers modifiers) {

            }

            @Override
            public void afterVisitAnnotation(Annotation annotation) {

            }

            @Override
            public void afterVisitAnnotationElement(AnnotationElement annotationElement) {

            }

            @Override
            public void afterVisitNormalTypeBody(NormalTypeBody normalTypeBody) {

            }

            @Override
            public void afterVisitEnumTypeBody(EnumTypeBody enumTypeBody) {

            }

            @Override
            public void afterVisitEmptyDeclaration(EmptyDeclaration emptyDeclaration) {

            }

            @Override
            public void afterVisitMethodDeclaration(MethodDeclaration methodDeclaration) {

            }

            @Override
            public void afterVisitConstructorDeclaration(ConstructorDeclaration constructorDeclaration) {

            }

            @Override
            public void afterVisitSuperConstructorInvocation(SuperConstructorInvocation superConstructorInvocation) {

            }

            @Override
            public void afterVisitAlternateConstructorInvocation(AlternateConstructorInvocation alternateConstructorInvocation) {

            }

            @Override
            public void afterVisitInstanceInitializer(InstanceInitializer instanceInitializer) {

            }

            @Override
            public void afterVisitStaticInitializer(StaticInitializer staticInitializer) {

            }

            @Override
            public void afterVisitClassDeclaration(ClassDeclaration classDeclaration) {

            }

            @Override
            public void afterVisitInterfaceDeclaration(InterfaceDeclaration interfaceDeclaration) {

            }

            @Override
            public void afterVisitEnumDeclaration(EnumDeclaration enumDeclaration) {

            }

            @Override
            public void afterVisitEnumConstant(EnumConstant enumConstant) {

            }

            @Override
            public void afterVisitAnnotationDeclaration(AnnotationDeclaration annotationDeclaration) {

            }

            @Override
            public void afterVisitAnnotationMethodDeclaration(AnnotationMethodDeclaration annotationMethodDeclaration) {

            }

            @Override
            public void afterVisitCompilationUnit(CompilationUnit compilationUnit) {

            }

            @Override
            public void afterVisitPackageDeclaration(PackageDeclaration packageDeclaration) {

            }

            @Override
            public void afterVisitImportDeclaration(ImportDeclaration importDeclaration) {

            }

            @Override
            public void afterVisitParseArtefact(Node node) {

            }

            @Override
            public void afterVisitComment(Comment comment) {

            }
        };
    }


}
