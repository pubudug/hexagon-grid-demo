package io.github.pubudug.hexgrid.demo;

import io.github.pubudug.hexgrid.HexagonFactory;

public class DemoHexagonFactory implements HexagonFactory<DemoHexagon, DemoCoordinate> {

    private int size;

    public DemoHexagonFactory(int size) {
        this.size = size;
    }

    @Override
    public DemoHexagon create(DemoCoordinate c) {
        return new DemoHexagon(c, size);
    }

}
