package br.ufrn.imd.lii.pidriver.cli;

public class CliDefs {

    public static final String SEARCH_VALUES_CMD = "search-values";
    public static final String HELP_CMD = "help";

    public static final String HELP_PARAM = "--help";
    public static final String HELP_PARAM_DESC = "Display usage manual";

    public static final String LONG_OPT_PI_HOST = "--pi-host";
    public static final String SHORT_OPT_PI_HOST = "-pih";
    public static final String OPT_DESC_PI_HOST = "PI host";

    public static final String LONG_OPT_DAS_HOST = "--das-host";
    public static final String SHORT_OPT_DAS_HOST = "-dash";
    public static final String OPT_DESC_DAS_HOST = "PI DAS host";

    public static final String SHORT_OPT_PI_USER = "-u";
    public static final String LONG_OPT_PI_USER = "--pi-user";
    public static final String OPT_DESC_PI_USER = "PI user";

    public static final String SHORT_OPT_PI_PASS = "-p";
    public static final String LONG_OPT_PI_PASS = "--pi-pass";
    public static final String OPT_DESC_PI_PASS = "PI password";

    public static final String SHORT_OPT_TAG = "-t";
    public static final String LONG_OPT_TAG = "--tag";
    public static final String OPT_DESC_TAG = "Tag to search for";

    public static final String SHORT_OPT_FROM = "-dtf";
    public static final String LONG_OPT_FROM = "--from";
    public static final String OPT_DESC_FROM = "Date to search from";

    public static final String SHORT_OPT_TO = "-dtt";
    public static final String LONG_OPT_TO = "--to";
    public static final String OPT_DESC_TO = "Date to search to";

    public static final String SHORT_OPT_MINV = "-mnv";
    public static final String LONG_OPT_MINV = "--min-value";
    public static final String OPT_DESC_MINV = "Minimum value to search";

    public static final String SHORT_OPT_MAXV = "-mxv";
    public static final String LONG_OPT_MAXV = "--max-value";
    public static final String OPT_DESC_MAXV = "Maximum value to search";

    public static final String SHORT_OPT_LIMIT = "-l";
    public static final String LONG_OPT_LIMIT = "--limit";
    public static final String OPT_DESC_LIMIT = "Maximum number of results to collect";

    public static final String SHORT_OPT_CLOSED_MNV = "-cmnv";
    public static final String LONG_OPT_CLOSED_MNV = "--closed-min-value";
    public static final String OPT_DESC_CLOSED_MNV = "Search considering the minimum value";

    public static final String SHORT_OPT_CLOSED_MXV = "-cmxv";
    public static final String LONG_OPT_CLOSED_MXV = "--closed-max-value";
    public static final String OPT_DESC_CLOSED_MXV = "Search considering the maximum value";

    public static final String SHORT_OPT_OUTPUT_FORMAT = "-o";
    public static final String LONG_OPT_OUTPUT_FORMAT = "--output";
    public static final String OPT_DESC_OUTPUT_FORMAT = "Output format of the result";

    public static final String SHORT_OPT_OUTPUT_PATH = "-p";
    public static final String LONG_OPT_OUTPUT_PATH = "--out-path";
    public static final String OPT_DESC_OUTPUT_PATH = "Path to output the results";

}
