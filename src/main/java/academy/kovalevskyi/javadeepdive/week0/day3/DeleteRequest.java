package academy.kovalevskyi.javadeepdive.week0.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;

import java.util.ArrayList;

public class DeleteRequest extends AbstractRequest<Csv> {
    Csv target;
    Selector selector;

//    private DeleteRequest(Builder builder) {
//       super(builder.csv);                       // Это правильно?
//       this.selector = builder.selector;
//    }
    private DeleteRequest(Csv target, Selector whereSelector) {
        super(target);
        selector = whereSelector;
        this.target = target;        // нужно ли использовать эту строчку или достаточно первой. Объясните пожалуйста.
    }

    @Override
    protected Csv execute() throws RequestException {
        Csv newCsv = null;
        ArrayList<String[]> list = new ArrayList<>();

        int i;

        for (i = 0; i < target.header().length; i++) {
            if (target.header()[i].equals(selector.fileName())) {
                break;
            }
        }
        for (String[] row : target.values()) {
            for (int j = 0; j < row.length; j++) {
                if (j == i) {
                    if (!row[j].equals(selector.value())) {
                        list.add(row);
                    }
                }
            }
        }
        String[][] val = new String[list.size()][];
        for (int j = 0; j < list.size(); j++) {
            val[j] = list.get(j);
        }

      //  newCsv = new Csv(target.header(), val);
        return new Csv(target.header(), val);
    }

    public static class Builder {
        Selector selector;
        Csv csv;

        public Builder where(Selector selector) {
            this.selector = selector;
            return this;
        }

        public Builder from(Csv csv) {
            this.csv = csv;
            return this;
        }

        public DeleteRequest build() {
            return new DeleteRequest(csv, selector);
        }
    }

}
