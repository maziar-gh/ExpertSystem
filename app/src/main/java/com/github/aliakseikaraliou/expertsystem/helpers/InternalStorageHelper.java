package com.github.aliakseikaraliou.expertsystem.helpers;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public final class InternalStorageHelper {

    public static void append(final Context pContext, final String pFileName, final String pData) {
        try {
            final OutputStream outputStream = pContext.openFileOutput(pFileName, Context.MODE_APPEND);
            outputStream.write(pData.getBytes());
            outputStream.close();
        } catch (final Exception pE) {
            throw new RuntimeException(pE);
        }
    }

    public static void appendLine(final Context pContext, final String pFileName, final String pData) {
        final String linedData = pData + "\n";
        try {
            final OutputStream outputStream = pContext.openFileOutput(pFileName, Context.MODE_APPEND);
            outputStream.write(linedData.getBytes());
            outputStream.close();
        } catch (final Exception pE) {
            throw new RuntimeException(pE);
        }
    }

    public static void rewrite(final Context pContext, final String pFileName, final String pData) {
        try {
            final OutputStream outputStream = pContext.openFileOutput(pFileName, Context.MODE_PRIVATE);
            outputStream.write(pData.getBytes());
            outputStream.close();
        } catch (final Exception pE) {
            throw new RuntimeException(pE);
        }
    }

    public static String readAll(final Context pContext, final String pFileName) {
        try {
            final InputStream inputStream = pContext.openFileInput(pFileName);

            final byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();

            return new String(bytes);
        } catch (final FileNotFoundException pE) {
            return "";
        } catch (final Exception pE) {
            throw new RuntimeException(pE);
        }
    }

    public static String readLine(final Context pContext, final String pFileName, final int pNumber) {
        try {
            final InputStream inputStream = pContext.openFileInput(pFileName);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            for (int i = 0; i < pNumber; ++i) {
                bufferedReader.readLine();
            }

            return bufferedReader.readLine();
        } catch (final FileNotFoundException pE) {
            return "";
        } catch (final Exception pE) {
            throw new RuntimeException(pE);
        }
    }

    public static List<String> readByLines(final Context pContext, final String pFileName) {
        try {
            final List<String> result = new ArrayList<>();

            final InputStream inputStream = pContext.openFileInput(pFileName);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }

            return result;
        } catch (final FileNotFoundException pE) {
            return new ArrayList<>();
        } catch (final Exception pE) {
            throw new RuntimeException(pE);
        }
    }
}
