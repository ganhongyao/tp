package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.student.GroupContainsKeywordsPredicate;
import seedu.address.model.student.IdContainsKeywordsPredicate;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.student.TagContainsKeywordsPredicate;


/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SearchCommand extends Command {

    public static final String COMMAND_WORD = "search";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all students whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "(" + PREFIX_NAME + "<student_name>" + " | "
            + PREFIX_ID + "<student_id>" + " | "
            + PREFIX_GROUP + "<group_name>" + " | "
            + PREFIX_TAG + "<tag>" + ")\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tang Zhiying, "
            + COMMAND_WORD + " "
            + PREFIX_GROUP + "T01A R04B";

    private final Predicate<Student> predicate;

    public SearchCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public SearchCommand(IdContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public SearchCommand(GroupContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public SearchCommand(TagContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        int numStudents = model.getFilteredStudentList().size();
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                        numStudents + " student" + (numStudents == 1 ? "" : "s")));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchCommand // instanceof handles nulls
                && predicate.equals(((SearchCommand) other).predicate)); // state check
    }
}
