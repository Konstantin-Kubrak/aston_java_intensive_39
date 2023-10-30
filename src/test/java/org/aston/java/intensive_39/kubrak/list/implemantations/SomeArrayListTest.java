package org.aston.java.intensive_39.kubrak.list.implemantations;

import org.aston.java.intensive_39.kubrak.list.interfaces.SomeList;
import org.junit.jupiter.api.*;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SomeArrayListTest {

    private static SomeList<String> someListString;


    private static final String TEST_STRING_0 = "testString0";
    private static final String TEST_STRING_1 = "testString1";


    @BeforeAll
    static void setUpInit() {

        someListString = new SomeArrayList<>();
    }


    @BeforeEach
    void setUp() {

        someListString.put(TEST_STRING_0);
        someListString.put(TEST_STRING_1);
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

        //then
        assertThat(someListString.size()).isEqualTo(initialSize + 1);

        for (int i = 0; i < someListString.size(); i++) {
            if (i == 1) {
                assertThat(someListString.get(i)).isEqualTo(testString2);
                assertThat(someListString.get(i + 1)).isEqualTo(TEST_STRING_1);
            } else {
                assertThat(someListString.get(i)).isNotEqualTo(testString2);
            }
        }
    }

    @DisplayName("Вставка,замена,удаление,получение элемента (negative)")
    @Test
    void indexOutOfBoundsTest() {

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {someListString.get(696);});
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {someListString.set("test", 696);});
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {someListString.delete(696);});
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {someListString.insert("test", 696);});
    }

    @DisplayName("Удаление элемента")
    @Test
    void deleteTestPositive() {

        //given
        int initialSize = someListString.size();

        //when
        someListString.delete(1);

        //then
        assertThat(someListString.size()).isEqualTo(initialSize - 1);
        for (int i = 0; i < someListString.size(); i++) {
            assertThat(someListString.get(i)).isNotEqualTo(TEST_STRING_1);
        }
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
        assertThat(someListString.size()).isZero();
        assertThat(someListString.isEmpty()).isTrue();
        assertThat(someListString.contains(TEST_STRING_0)).isFalse();
        assertThat(someListString.contains(TEST_STRING_1)).isFalse();
    }

    @DisplayName("Проверка пустая ли коллекция (negative)")
    @Test
    void isEmptyTestNegative() {


        assertThat(someListString.size()).isNotZero();
        assertThat(someListString.isEmpty()).isFalse();
        assertThat(someListString.contains(TEST_STRING_0)).isTrue();
        assertThat(someListString.contains(TEST_STRING_1)).isTrue();
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
        assertThat(someListString.size()).isZero();
        assertThat(someListString.contains(TEST_STRING_0)).isFalse();
        assertThat(someListString.contains(TEST_STRING_1)).isFalse();
    }

    @DisplayName("Увеличение вместимости коллекции")
    @Test
    void checkCapacityExtensionTest() {

        //given
        int initialSize = someListString.size();

        //when
        for (int i = 0; i < 1000; i++) {
            someListString.put(TEST_STRING_0);
        }

        //then
        assertThat(someListString.size()).isEqualTo(initialSize + 1000);
        for (int i = 0; i < 10; i++) {
            assertThat(someListString.get(i)).isNotNull();
        }

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

        //then
        assertThat(someListString.size()).isEqualTo(initialSize);

        for (int i = 0; i < someListString.size(); i++) {
            if (i == 1) {
                assertThat(someListString.get(i)).isEqualTo(testString2);
            } else {
                assertThat(someListString.get(i)).isNotEqualTo(testString2);
            }
        }
    }

    @DisplayName("Получение размера коллекции")
    @Test
    void getLengthTest() {

        assertThat(someListString.size()).isEqualTo(2);
    }
}