package ru.naumen.sd40.log.parser.sdng;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.DataParser;
import ru.naumen.sd40.log.parser.data.ActionDoneData;
import ru.naumen.sd40.log.parser.data.ErrorData;
import ru.naumen.sd40.log.parser.dataset.SdngDataSet;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SdngDataParser implements DataParser<SdngDataSet> {
    private static final Pattern DONE_REG_EX = Pattern.compile("Done\\((\\d+)\\): ?(.*?Action)");
    private static final Pattern WARN_REG_EX = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) WARN");
    private static final Pattern ERROR_REG_EX = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) ERROR");
    private static final Pattern FATAL_REG_EX = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) FATAL");

    private static Set<String> EXCLUDED_ACTIONS = new HashSet<>();

    static
    {
        EXCLUDED_ACTIONS.add("EventAction".toLowerCase());
    }

    @Override
    public void parseLine(String line, SdngDataSet dataSet) {
        parseErrorLine(line, dataSet);
        parseActionLine(line, dataSet);
    }

    public void parseErrorLine(String line, SdngDataSet dataSet)
    {
        ErrorData errorData = dataSet.getErrorsData();
        if (WARN_REG_EX.matcher(line).find())
        {
            errorData.incrementWarnCount();
        }
        if (ERROR_REG_EX.matcher(line).find())
        {
            errorData.incrementErrorCount();
        }
        if (FATAL_REG_EX.matcher(line).find())
        {
            errorData.incrementFatalCount();
        }
    }

    public void parseActionLine(String line, SdngDataSet dataSet) {
        ActionDoneData actionDoneData = dataSet.getActionsDoneData();
        Matcher matcher = DONE_REG_EX.matcher(line);
        if (matcher.find())
        {
            String actionInLowerCase = matcher.group(2).toLowerCase();
            if (EXCLUDED_ACTIONS.contains(actionInLowerCase))
            {
                return;
            }

            actionDoneData.getTimes().add(Integer.parseInt(matcher.group(1)));
            if (actionInLowerCase.equals("addobjectaction"))
            {
                actionDoneData.incrementAddObjectActions();
            }
            else if (actionInLowerCase.equals("editobjectaction"))
            {
                actionDoneData.incrementEditObjectsActions();
            }
            else if (actionInLowerCase.matches("(?i)[a-zA-Z]+comment[a-zA-Z]+"))
            {
                actionDoneData.incrementCommentActions();
            }
            else if (!actionInLowerCase.contains("advlist")
                    && actionInLowerCase.matches("(?i)^([a-zA-Z]+|Get)[a-zA-Z]+List[a-zA-Z]+"))

            {
                actionDoneData.incrementGetListActions();
            }
            else if (actionInLowerCase.matches("(?i)^([a-zA-Z]+|Get)[a-zA-Z]+Form[a-zA-Z]+"))
            {
                actionDoneData.incrementGetFormActions();
            }
            else if (actionInLowerCase.matches("(?i)^([a-zA-Z]+|Get)[a-zA-Z]+DtObject[a-zA-Z]+"))
            {
                actionDoneData.incrementGetDtObjectActions();
            }
            else if (actionInLowerCase.matches("(?i)[a-zA-Z]+search[a-zA-Z]+"))
            {
                actionDoneData.incrementSearchActions();
            }
            else if (actionInLowerCase.equals("getcatalogsaction"))
            {
                actionDoneData.incrementGetCatalogsAction();
            }
        }
    }
}
