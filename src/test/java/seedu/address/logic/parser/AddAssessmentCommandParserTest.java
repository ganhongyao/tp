package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ASSESSMENT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ASSESSMENT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ASSESSMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSESSMENT_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAssessmentCommand;
import seedu.address.model.student.Assessment;

public class AddAssessmentCommandParserTest {
    private AddAssessmentCommandParser parser = new AddAssessmentCommandParser();

    @Test
    public void parse_expectedInput_success() {
        AddAssessmentCommand expectedCommand = new AddAssessmentCommand(new Assessment(VALID_ASSESSMENT_AMY));
        assertParseSuccess(parser, ASSESSMENT_DESC_AMY, expectedCommand);
    }

    @Test
    public void parse_multipleAssessments_success() {
        AddAssessmentCommand expectedCommand = new AddAssessmentCommand(new Assessment(VALID_ASSESSMENT_AMY));
        assertParseSuccess(parser, ASSESSMENT_DESC_BOB + ASSESSMENT_DESC_AMY, expectedCommand);
    }

    @Test
    public void parse_extraWhitespace_success() {
        AddAssessmentCommand expectedCommand = new AddAssessmentCommand(new Assessment(VALID_ASSESSMENT_AMY));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ASSESSMENT_DESC_AMY, expectedCommand);
    }

    @Test
    public void parse_invalidAssessment_failure() {
        assertParseFailure(parser, INVALID_ASSESSMENT_DESC, Assessment.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingAssessment_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssessmentCommand.MESSAGE_USAGE));
    }
}
