package seedu.address.logic;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CheckoutCommand;
import seedu.address.logic.commands.ClearCommand;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.UndoCommand;

import java.util.Arrays;
import java.util.List;

/**
 * Stores list of all available commands
 */
public class CommandList {
    public static final List<String> COMMAND_LIST = Arrays.asList(AddCommand.COMMAND_WORD,
            CheckoutCommand.COMMAND_WORD, ClearCommand.COMMAND_WORD, 
            EditCommand.COMMAND_WORD, ExitCommand.COMMAND_WORD, FindCommand.COMMAND_WORD,
            HelpCommand.COMMAND_WORD, HistoryCommand.COMMAND_WORD, ListCommand.COMMAND_WORD,
            SelectCommand.COMMAND_WORD, UndoCommand.COMMAND_WORD);
}
