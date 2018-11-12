package ru.naumen.sd40.log.parser;

import org.junit.Assert;
import org.junit.Test;
import ru.naumen.sd40.log.parser.sdng.SdngDataParser;

public class ActionDoneParserTest{
    private DataSet dataSet = DataSet.getInstance();

    @Test
    public void mustParseAddAction() {
        //given
        SdngDataParser parser = new SdngDataParser();

        //when
        parser.parseActionLine("Done(10): AddObjectAction", dataSet);

        //then
        Assert.assertEquals(1, dataSet.getActionsDoneData().getAddObjectActions());
    }

    @Test
    public void mustParseFormActions() {
        //given
        SdngDataParser parser = new SdngDataParser();

        //when
        parser.parseActionLine("Done(10): GetFormAction", dataSet);
        parser.parseActionLine("Done(1): GetAddFormContextDataAction", dataSet);

        //then
        Assert.assertEquals(2, dataSet.getActionsDoneData().getFormActions());
    }

    @Test
    public void mustParseEditObject() {
        //given
        SdngDataParser parser = new SdngDataParser();

        //when
        parser.parseActionLine("Done(10): EditObjectAction", dataSet);

        //then
        Assert.assertEquals(1, dataSet.getActionsDoneData().getEditObjectsActions());
    }

    @Test
    public void mustParseSearchObject(){
        //given
        SdngDataParser parser = new SdngDataParser();

        //when
        parser.parseActionLine("Done(10): GetPossibleAgreementsChildsSearchAction", dataSet);
        parser.parseActionLine("Done(10): TreeSearchAction", dataSet);
        parser.parseActionLine("Done(10): GetSearchResultAction", dataSet);
        parser.parseActionLine("Done(10): GetSimpleSearchResultsAction", dataSet);
        parser.parseActionLine("Done(10): SimpleSearchAction", dataSet);
        parser.parseActionLine("Done(10): ExtendedSearchByStringAction", dataSet);
        parser.parseActionLine("Done(10): ExtendedSearchByFilterAction", dataSet);

        //then
        Assert.assertEquals(7, dataSet.getActionsDoneData().getSearchActions());
    }

    @Test
    public void mustParseGetList(){
        //given:
        SdngDataParser parser=  new SdngDataParser();

        //when:
        parser.parseActionLine("Done(10): GetDtObjectListAction", dataSet);
        parser.parseActionLine("Done(10): GetPossibleCaseListValueAction", dataSet);
        parser.parseActionLine("Done(10): GetPossibleAgreementsTreeListActions", dataSet);
        parser.parseActionLine("Done(10): GetCountForObjectListAction", dataSet);
        parser.parseActionLine("Done(10): GetDataForObjectListAction", dataSet);
        parser.parseActionLine("Done(10): GetPossibleAgreementsListActions", dataSet);
        parser.parseActionLine("Done(10): GetDtObjectForRelObjListAction", dataSet);

        //then:
        Assert.assertEquals(7, dataSet.getActionsDoneData().geListActions());
    }

    @Test
    public void mustParseGetCatalogsAction(){
        //given
        SdngDataParser parser = new SdngDataParser();

        //when
        parser.parseActionLine("Done(10): GetCatalogsAction", dataSet);

        //then
        Assert.assertEquals(1, dataSet.getActionsDoneData().getGetCatalogsAction());
    }

    @Test
    public void mustParseComment(){
        //given:
        SdngDataParser parser=  new SdngDataParser();

        //when:
        parser.parseActionLine("Done(10): EditCommentAction", dataSet);
        parser.parseActionLine("Done(10): ChangeResponsibleWithAddCommentAction", dataSet);
        parser.parseActionLine("Done(10): ShowMoreCommentAttrsAction", dataSet);
        parser.parseActionLine("Done(10): CheckObjectsExceedsCommentsAmountAction", dataSet);
        parser.parseActionLine("Done(10): GetAddCommentPermissionAction", dataSet);
        parser.parseActionLine("Done(10): GetCommentDtObjectTemplateAction", dataSet);

        //then:
        Assert.assertEquals(6, dataSet.getActionsDoneData().getCommentActions());
    }

    @Test
    public void mustParseDtObject(){
        //given:
        SdngDataParser parser=  new SdngDataParser();

        //when:
        parser.parseActionLine("Done(10): GetVisibleDtObjectAction", dataSet);
        parser.parseActionLine("Done(10): GetDtObjectsAction", dataSet);
        parser.parseActionLine("Done(10): GetDtObjectTreeSelectionStateAction", dataSet);
        parser.parseActionLine("Done(10): AbstractGetDtObjectTemplateAction", dataSet);
        parser.parseActionLine("Done(10): GetDtObjectTemplateAction", dataSet);

        //then:
        Assert.assertEquals(5, dataSet.getActionsDoneData().getDtObjectActions());
    }

}
