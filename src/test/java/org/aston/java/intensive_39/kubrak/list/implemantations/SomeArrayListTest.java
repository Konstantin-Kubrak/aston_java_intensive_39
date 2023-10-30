package org.aston.java.intensive_39.kubrak.list.implemantations;

import org.aston.java.intensive_39.kubrak.list.interfaces.SomeList;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SomeArrayListTest {

    private static SomeList<String> someListString;

    private static String testString0;
    private static String testString1;


    @BeforeAll
    static void setUpInit() {
        someListString = new SomeArrayList<>();
        testString0 = "testString0";
        testString1 = "testString1";
    }


    @BeforeEach
    void setUp() {

        someListString.put(testString0);
        someListString.put(testString1);
    }

    @AfterEach
    void cleanUp() {

        someListString.deleteAll();
    }

    @DisplayName("Сохранение и получение элемента")
    @Test
    void putAndGetTest() {
        //given
        String testString2 = "testString2";
        String testString3 = "testString3";
        int initialSize = someListString.size();

        //when
        someListString.put(testString2);
        someListString.put(testString3);
        String testString2Check = someListString.get(2);
        String testString3Check = someListString.get(3);

        //then
        assertThat(someListString.size()).isEqualTo(initialSize + 2);
        assertThat(testString2Check).isEqualTo(testString2);
        assertThat(testString3Check).isEqualTo(testString3);

    }

    @DisplayName("Вставка элемента")
    @Test
    void insertTest() {

        //given
        String testString2 = "testString2";
        int initialSize = someListString.size();

        //when
        someListString.insert(testString2, 1);
        String testString2Check = someListString.get(1);

        //then
        assertThat(testString2Check).isEqualTo(testString2);
        assertThat(someListString.size()).isEqualTo(initialSize + 1);
        assertThat(someListString.get(2)).isEqualTo(testString1);

    }

    @DisplayName("Удаление элемента")
    @Test
    void deleteTest() {

        //given
        int initialSize = someListString.size();

        //when
        someListString.delete(1);

        //then
        assertThat(someListString.size()).isEqualTo(initialSize - 1);
        assertThat(someListString.contains(testString1)).isFalse();
    }

    @DisplayName("Проверка пустая ли коллекция (positive)")
    @Test
    void isEmptyTestPositive() {

        //given
        int initialSize = someListString.size();

        //when
        someListString.deleteAll();

        //then
        assertThat(someListString.size()).isNotEqualTo(initialSize);
        assertThat(someListString.size()).isEqualTo(0);
        assertThat(someListString.isEmpty()).isTrue();
        assertThat(someListString.contains(testString0)).isFalse();
        assertThat(someListString.contains(testString1)).isFalse();
    }

    @DisplayName("Проверка пустая ли коллекция (negative)")
    @Test
    void isEmptyTestNegative() {

        //given
        int initialSize = someListString.size();

        //when
        someListString.deleteAll();

        //then
        assertThat(someListString.size()).isNotEqualTo(initialSize);
        assertThat(someListString.size()).isEqualTo(0);
        assertThat(someListString.isEmpty()).isTrue();
        assertThat(someListString.contains(testString0)).isFalse();
        assertThat(someListString.contains(testString1)).isFalse();
    }

    @DisplayName("Удаление всех элементов коллекции")
    @Test
    void deleteAllTest() {

        //given
        int initialSize = someListString.size();

        //when
        someListString.deleteAll();

        //then
        assertThat(someListString.size()).isNotEqualTo(initialSize);
        assertThat(someListString.size()).isEqualTo(0);
        assertThat(someListString.contains(testString0)).isFalse();
        assertThat(someListString.contains(testString1)).isFalse();
    }

    @DisplayName("Увеличение вместимости коллекции")
    @Test
    void checkCapacityExtensionTest() {

        //given
        int initialSize = someListString.size();

        //when
        someListString.put(testString0);
        someListString.put(testString0);
        someListString.put(testString0);
        someListString.put(testString0);
        someListString.put(testString0);
        someListString.put(testString0);
        someListString.put(testString0);
        someListString.put(testString0);

        //then
        assertThat(someListString.size()).isEqualTo(initialSize + 8);
    }

    @DisplayName("Сортировка")
    @Test
    void sortTest() {

        //given
        someListString.put("testString5");
        someListString.put("testString4");
        someListString.put("testString3");
        someListString.put("testString2");

        //when
        someListString.sort();

        //then
        for (int i = 0; i < someListString.size(); i++) {
            assertThat(someListString.get(i)).isEqualTo("testString" + i);
        }
    }

    @DisplayName("Сортировка с компаратором")
    @Test
    void sortWitchComparatorTest() {

        //given
        someListString.put("testString3");
        someListString.put("testString2");
        someListString.put("testString4");
        someListString.put("testString5");
        int size = someListString.size() - 1;

        //when
        someListString.sort(Comparator.reverseOrder());

        //then
        for (int i = 0; i < someListString.size(); i++) {
            assertThat(someListString.get(i)).isEqualTo("testString" + (size - i));
        }
    }

    @DisplayName("Замена элемента коллекции")
    @Test
    void setTest() {

        //given
        String testString2 = "testString2";
        int initialSize = someListString.size();

        //when
        someListString.set(testString2, 1);
        String testString2Check = someListString.get(1);

        //then
        assertThat(testString2Check).isEqualTo(testString2);
        assertThat(someListString.size()).isEqualTo(initialSize);
    }

    @DisplayName("Получение размера коллекции")
    @Test
    void getLengthTest() {

        assertThat(someListString.size()).isEqualTo(2);
    }
}