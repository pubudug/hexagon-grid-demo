package io.github.pubudug.hexgrid.demo;

import java.util.List;

public class TerrainMap {
    private List<String> lines;

    public TerrainMap(TerrainMapReader reader) {
        this.lines = reader.readLines();
    }

    int getRows() {
        return lines.size();
    }

    int getColumns() {
        return lines.get(0).length();
    }

    TerrainType getTerrain(int column, int row) {
        char typeChar = lines.get(row).charAt(column);
        return TerrainType.getByCharacter(typeChar);
    }

}
