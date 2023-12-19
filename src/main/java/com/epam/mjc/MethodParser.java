package com.epam.mjc;

import java.util.*;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String outsideBrackets = signatureString.split("\\(")[0];
        String insideBrackets = signatureString.split("\\(")[1];
        String parameters = insideBrackets.substring(0, insideBrackets.length() - 1);
        String accessModifier = "";
        String returnType;
        String methodName;
        String[] left = outsideBrackets.split(" ");
        if (left.length == 3) {
            accessModifier = left[0];
            returnType = left[1];
            methodName = left[2];
        } else {
            returnType = left[0];
            methodName = left[1];
        }

        String[] right = parameters.split(", ");
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        for (int i = 0; i < right.length; i++) {
            MethodSignature.Argument argument = new MethodSignature.Argument(right[i].split(" ")[0], right[i].split(" ")[1]);
            arguments.add(argument);
        }

        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setReturnType(returnType);
        if (!accessModifier.isEmpty()) {
            methodSignature.setAccessModifier(accessModifier);
        }

        return methodSignature;
    }
}
