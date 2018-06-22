package com.retronic.persistence.utils;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MockUtils {

    public static Answer buildAnswerGet(final int elementId, final Map map) {
        return new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return map.get(invocation.getArguments()[0]);
            }
        };
    }

    public static Answer buildAnswerGetAll(final Map<Integer, Serializable> map) {
        return new Answer<List>() {
            public List answer(InvocationOnMock invocation) throws Throwable {
                List elements = new ArrayList();

                for (Map.Entry<Integer, Serializable> entry : map.entrySet()) {
                    elements.add(entry.getValue());
                }

                return elements;
            }
        };
    }
}
