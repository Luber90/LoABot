/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package put.ai.games.linesofaction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import put.ai.games.engine.BoardFactory;
import put.ai.games.engine.parameters.IntegerParameter;
import put.ai.games.engine.parameters.Parameter;

public class LinesOfActionBoardFactory implements BoardFactory {

    private static final List<? extends Parameter<?>> PARAMS = Collections.unmodifiableList(Arrays
            .asList(new IntegerParameter(BOARD_SIZE, 8, 8, 20)));
    private int size = (Integer) PARAMS.get(0).getDefaultValue();


    @Override
    public LinesOfActionBoard create() {
        return new LinesOfActionBoard(size);
    }


    @Override
    public List<? extends Parameter<?>> getConfigurationOptions() {
        return PARAMS;
    }


    @Override
    public void configure(Map<String, Object> configuration) {
        size = Parameter.<Integer> get(BOARD_SIZE, PARAMS, configuration);
    }


    @Override
    public String getName() {
        return "Lines of Action";
    }


    @Override
    public String getRules() {
        return "http://en.wikipedia.org/wiki/Lines_of_Action";
    }
}
