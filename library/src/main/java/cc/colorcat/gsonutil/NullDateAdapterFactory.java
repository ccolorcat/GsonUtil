/*
 * Copyright 2018 cxx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.colorcat.gsonutil;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
public class NullDateAdapterFactory implements TypeAdapterFactory {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final String format;

    public NullDateAdapterFactory() {
        this(DEFAULT_FORMAT);
    }

    public NullDateAdapterFactory(String format) {
        this.format = format;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<? super T> rawType = type.getRawType();
        if (!Date.class.isAssignableFrom(rawType)) {
            return null;
        }
        return (TypeAdapter<T>) new NullDateAdapter(format);
    }


    private static class NullDateAdapter extends TypeAdapter<Date> {
        private final DateFormat dateFormatter;

        private NullDateAdapter(String format) {
            dateFormatter = new SimpleDateFormat(format, Locale.getDefault());
        }

        @Override
        public void write(JsonWriter out, Date value) throws IOException {
            synchronized (dateFormatter) {
                if (value == null) {
                    out.nullValue();
                } else {
                    String dateString = dateFormatter.format(value);
                    out.value(dateString);
                }
            }
        }

        @Override
        public synchronized Date read(JsonReader in) throws IOException {
            JsonToken token = in.peek();
            if (token == JsonToken.NULL) {
                in.nextNull();
                return new Date();
            }
            if (token != JsonToken.STRING) {
                throw new JsonParseException("The date should be a string value");
            }
            try {
                return dateFormatter.parse(in.nextString());
            } catch (ParseException e) {
                throw new JsonParseException(e);
            }
        }
    }
}
