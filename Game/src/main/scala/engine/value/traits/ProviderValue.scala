package engine.value.traits

import engine.GameContext
import engine.value.Value
import engine.value.ValueTypes.Provider

trait ProviderValue[R] extends Value[R] {
    protected val provider: Provider[R]

    final override def get(implicit gameContext: GameContext): Option[R] =
        provider(gameContext)
}
