/*
 * Copyright 2017 Kailuo Wang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cats.hkinds

import cats.data.Tuple2K
import cats.tagless.{ContravariantK, SemigroupalK}
import cats.~>
import simulacrum.typeclass


@typeclass trait ContravariantSemigroupalK[A[_[_]]] extends SemigroupalK[A] with ContravariantK[A] {
  def contramap2K[F[_], G[_], H[_]](af: A[F], ag: A[G])(f: H ~> Tuple2K[F, G, ?]): A[H] =
    contramapK(productK(af, ag))(f)
}
