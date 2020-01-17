package com.conaxgames.practice.duel;

import com.conaxgames.practice.kit.Kit;
import lombok.Data;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Data
public class MatchRequest {

    private final UUID requester;
    private final UUID requestee;
    private final Kit kit;

    private final long creationTime = System.currentTimeMillis();

    /**
     * Returns whether or not this request was created
     * more than 30 seconds ago.
     *
     * @return true if this request was sent more than 30 seconds ago, otherwise false
     */
    public boolean hasExpired() {
        return System.currentTimeMillis() - creationTime >= TimeUnit.SECONDS.toMillis(30);
    }

}
