package net.bram91.modeldumper.types;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class AnimationGroup{
    @Getter
    private final List<Animation> animationGroup;
}