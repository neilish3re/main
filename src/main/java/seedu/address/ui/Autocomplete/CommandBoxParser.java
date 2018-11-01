package seedu.address.ui.Autocomplete;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
/**
 * Parses text user has entered in the Command Box
 */
public class CommandBoxParser {

    protected static final int COMMAND_INDEX = 0;
    protected static final int ARGUMENT_INDEX = 1;

    private static final String EMPTY_STRING = "";

    /**
     * Used for initial separation of command word and arguments
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static final Prefix[] ALL_PREFIXES = {PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL,
            PREFIX_TAG, PREFIX_ROOM, PREFIX_INDEX};

    /**
     * Parses text in Command Box to see if it contains any occurrences of Command and/or Prefix
     *
     * String array is return containing the Command at the zeroth index and the remaining arguments from the
     * 1st index
     */
    public String[] parseCommandAndPrefixes(String cmdbox){
        requireNonNull(cmdbox);
        String parseResults[] = {EMPTY_STRING, EMPTY_STRING};
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(cmdbox.trim());
        if (!matcher.matches()){
            return parseResults;
        }
        final String commandEntry = matcher.group("commandEntry");
        final String arguments = matcher.group("arguments");
        if (isValidCommand(commandEntry)){
            parseResults[COMMAND_INDEX] = commandEntry;
        }
        parseResults[ARGUMENT_INDEX] = arguments;
        return parseResults;
    }

    /**
     * Returns arraylist of prefixes missing from argument
     */
    public ArrayList<String> getMissingPrefixes(String arg) {
        requireNonNull(arg);
        Prefix[] prefixes = ALL_PREFIXES;
        ArrayList<String> missingPrefixes = new ArrayList<>();
        ArgumentMultimap argMap = ArgumentTokenizer.tokenize(arg, prefixes);
        Arrays.stream(ALL_PREFIXES)
                .filter(p -> isMissing(argMap, p))
                .forEach(p -> missingPrefixes.add(p.toString()));
        return missingPrefixes;
    }
    private boolean isMissing(ArgumentMultimap argMap, Prefix prefix) {
        return prefix.equals(PREFIX_TAG)
                || !argMap.getValue(prefix).isPresent()
                || argMap.getValue(prefix).get().equals(EMPTY_STRING);
    }
    private boolean isValidCommand(String cmd){
        return Arrays.asList(AutocompleteCommand.ALL_COMMANDS).contains(cmd);
    }

}
