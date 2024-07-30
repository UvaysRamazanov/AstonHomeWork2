package ru.ram;

import java.util.*;

public class CarArrayList implements CarList {

    private final byte initialSize = 10; // Начальный размер для нашего массива
    private Car[] array = new Car[initialSize];
    private int size = 0;
    private ArrayList<Car> cars;

    @Override
    public void add(int index, Car car) {
        if (size >= array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = car;
        size++;
    }

    @Override
    public void addAll(Collection<? extends Car> cars) {
        for (Car car : cars) {
            add(size, car);
        }
    }

    @Override
    public void clear() {
        Arrays.fill(array, 0, size, null);
        size = 0;
    }

    @Override
    public Car get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean removeCar(Car car) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(car)) {
                return remove(i);
            }
        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        checkIndex(index);
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        size--;
        array[size] = null; // Очистить ссылку для сборщика мусора
        return true;
    }

    @Override
    public void sort(Comparator<? super Car> comparator) {
        // Сортировка с использованием merge sort
        ArrayList<Car> carList = getListOfCars(); // Получить список автомобилей
        mergeSort(carList, comparator);
        // Копируем отсортированный результат обратно в основной массив
        for (int i = 0; i < carList.size(); i++) {
            array[i] = carList.get(i);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void mergeSort(ArrayList<Car> cars, Comparator<? super Car> comparator) {
        if (cars.size() < 2) {
            return;
        }

        int mid = cars.size() / 2;
        ArrayList<Car> leftList = new ArrayList<>(cars.subList(0, mid));
        ArrayList<Car> rightList = new ArrayList<>(cars.subList(mid, cars.size()));

        mergeSort(leftList, comparator);
        mergeSort(rightList, comparator);

        merge(cars, leftList, rightList, comparator);
    }

    private void merge(ArrayList<Car> cars, ArrayList<Car> leftList, ArrayList<Car> rightList, Comparator<? super Car> comparator) {
        int leftIndex = 0;
        int rightIndex = 0;
        int carsIndex = 0;

        while (leftIndex < leftList.size() && rightIndex < rightList.size()) {
            if (comparator.compare(leftList.get(leftIndex), rightList.get(rightIndex)) <= 0) {
                cars.set(carsIndex++, leftList.get(leftIndex++));
            } else {
                cars.set(carsIndex++, rightList.get(rightIndex++));
            }
        }

        while (leftIndex < leftList.size()) {
            cars.set(carsIndex++, leftList.get(leftIndex++));
        }

        while (rightIndex < rightList.size()) {
            cars.set(carsIndex++, rightList.get(rightIndex++));
        }
    }

    private ArrayList<Car> getListOfCars() {
        return new ArrayList<>(Arrays.asList(array).subList(0, size));
    }

    public int getSize() {
        return size;
    }
}
