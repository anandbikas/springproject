/**
 * @author Bikas Anand
 */
package com.anand.springproject.domains;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"state"})
public class State {

    private int state;

    /**
     *
     * @return
     */
    public int getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "State{" +
                "state=" + state +
                '}';
    }

}
