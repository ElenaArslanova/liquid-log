package ru.naumen.sd40.log.parser;

import org.junit.Assert;
import org.junit.Test;

public class ActionDoneParserTest{
    private DataSet dataSet = new DataSet();

    @Test
    public void mustParseAddAction() {
        //given
        ActionDoneParser parser = new ActionDoneParser();

        //when
        parser.parseLine("Done(10): AddObjectAction", dataSet);

        //then
        Assert.assertEquals(1, parser.getAddObjectActions());
    }

    @Test
    public void mustParseFormActions() {
        //given
        ActionDoneParser parser = new ActionDoneParser();

        //when
        parser.parseLine("Done(10): GetFormAction", dataSet);
        parser.parseLine("Done(1): GetAddFormContextDataAction", dataSet);

        //then
        Assert.assertEquals(2, parser.getFormActions());
    }

    @Test
    public void mustParseEditObject() {
        //given
        ActionDoneParser parser = new ActionDoneParser();

        //when
        parser.parseLine("Done(10): EditObjectAction", dataSet);

        //then
        Assert.assertEquals(1, parser.getEditObjectsActions());
    }

    @Test
    public void mustParseSearchObject(){
        //given
        ActionDoneParser parser = new ActionDoneParser();

        //when
        parser.parseLine("Done(10): GetPossibleAgreementsChildsSearchAction", dataSet);
        parser.parseLine("Done(10): TreeSearchAction", dataSet);
        parser.parseLine("Done(10): GetSearchResultAction", dataSet);
        parser.parseLine("Done(10): GetSimpleSearchResultsAction", dataSet);
        parser.parseLine("Done(10): SimpleSearchAction", dataSet);
        parser.parseLine("Done(10): ExtendedSearchByStringAction", dataSet);
        parser.parseLine("Done(10): ExtendedSearchByFilterAction", dataSet);

        //then
        Assert.assertEquals(7, parser.getSearchActions());
    }

    @Test
    public void mustParseGetList(){
        //given:
        ActionDoneParser parser=  new ActionDoneParser();

        //when:
        parser.parseLine("Done(10): GetDtObjectListAction", dataSet);
        parser.parseLine("Done(10): GetPossibleCaseListValueAction", dataSet);
        parser.parseLine("Done(10): GetPossibleAgreementsTreeListActions", dataSet);
        parser.parseLine("Done(10): GetCountForObjectListAction", dataSet);
        parser.parseLine("Done(10): GetDataForObjectListAction", dataSet);
        parser.parseLine("Done(10): GetPossibleAgreementsListActions", dataSet);
        parser.parseLine("Done(10): GetDtObjectForRelObjListAction", dataSet);

        //then:
        Assert.assertEquals(7, parser.geListActions());
    }

    @Test
    public void mustParseGetCatalogsAction(){
        //given
        ActionDoneParser parser = new ActionDoneParser();

        //when
        parser.parseLine("Done(10): GetCatalogsAction", dataSet);

        //then
        Assert.assertEquals(1, parser.getGetCatalogsAction());
    }

    @Test
    public void mustParseComment(){
        //given:
        ActionDoneParser parser=  new ActionDoneParser();

        //when:
        parser.parseLine("Done(10): EditCommentAction", dataSet);
        parser.parseLine("Done(10): ChangeResponsibleWithAddCommentAction", dataSet);
        parser.parseLine("Done(10): ShowMoreCommentAttrsAction", dataSet);
        parser.parseLine("Done(10): CheckObjectsExceedsCommentsAmountAction", dataSet);
        parser.parseLine("Done(10): GetAddCommentPermissionAction", dataSet);
        parser.parseLine("Done(10): GetCommentDtObjectTemplateAction", dataSet);

        //then:
        Assert.assertEquals(6, parser.getCommentActions());
    }

    @Test
    public void mustParseDtObject(){
        //given:
        ActionDoneParser parser=  new ActionDoneParser();

        //when:
        parser.parseLine("Done(10): GetVisibleDtObjectAction", dataSet);
        parser.parseLine("Done(10): GetDtObjectsAction", dataSet);
        parser.parseLine("Done(10): GetDtObjectTreeSelectionStateAction", dataSet);
        parser.parseLine("Done(10): AbstractGetDtObjectTemplateAction", dataSet);
        parser.parseLine("Done(10): GetDtObjectTemplateAction", dataSet);

        //then:
        Assert.assertEquals(5, parser.getDtObjectActions());
    }

}
