package com.conaxgames.practice.duel;

import com.conaxgames.practice.kit.Kit;
import com.conaxgames.util.ttl.TtlHashMap;
import io.netty.util.internal.ConcurrentSet;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class DuelManager {

    private final Map<UUID, Set<MatchRequest>> playerToMatchRequestsMap = new TtlHashMap<>(TimeUnit.SECONDS, 30);

    /**
     * Attempts to get a {@link MatchRequest} with the specified
     * requester and requestee.
     *
     * @param requester the person that requested the match
     * @param requestee the person that's receiving the request
     * @return the request if found, otherwise null
     */
    public MatchRequest getRequest(UUID requester, UUID requestee) {
        if (!playerToMatchRequestsMap.containsKey(requestee)) {
            return null;
        }

        return playerToMatchRequestsMap.get(requestee).stream()
                .filter(request -> request.getRequester() == requester)
                .findFirst().orElse(null);
    }

    /**
     * Creates a match request between the {@code requester} and
     * {@code requestee} with the specified {@code kit}.
     *
     * @param requester the person that requested the match
     * @param requestee the person that's receiving the request
     * @param kit       the request kit
     */
    public void createRequest(UUID requester, UUID requestee, Kit kit) {
        MatchRequest request = new MatchRequest(requester, requestee, kit);

        playerToMatchRequestsMap.computeIfAbsent(requestee, uuid -> new HashSet<>()).add(request);
    }
}
