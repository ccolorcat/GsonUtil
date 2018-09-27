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
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.lang.reflect.Type;

public class GsonUtils {
    private static Gson gson;

    public static void setGson(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }
        GsonUtils.gson = gson;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .serializeNulls()
                    .registerTypeAdapterFactory(new NullStringAdapterFactory())
                    .registerTypeAdapterFactory(new NullMultiDateAdapterFactory("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"))
                    .registerTypeAdapterFactory(new NullArrayTypeAdapterFactory())
                    .registerTypeAdapterFactory(new NullCollectionTypeAdapterFactory())
                    .create();
        }
        return gson;
    }

    public static String toJson(Object obj) {
        return getGson().toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return getGson().fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return getGson().fromJson(json, typeOfT);
    }

    public static <T> T fromJson(Reader reader, Type typeOfT) {
        return getGson().fromJson(reader, typeOfT);
    }

    private GsonUtils() { throw new AssertionError("no instance"); }
}
