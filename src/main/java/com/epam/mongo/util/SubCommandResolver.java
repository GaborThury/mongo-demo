package com.epam.mongo.util;

import com.epam.mongo.domain.SubCommand;

public class SubCommandResolver {

    public static SubCommand resolve(int input) {
        return switch (input) {
            case 1 -> SubCommand.INSERT;
            case 2 -> SubCommand.UPDATE;
            case 3 -> SubCommand.DELETE;
            default -> throw new IllegalArgumentException("Given subcommand is invalid. " + input);
        };
    }

}
