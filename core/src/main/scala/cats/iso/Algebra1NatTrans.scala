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

package cats.iso

trait Algebra1NatTrans[-Alg1[_[_],_], +Alg2[_[_],_]] { self =>
  def apply[F[_], A](alg1: Alg1[F, A]): Alg2[F, A]

  def compose[Alg[_[_],_]](alg3: Algebra1NatTrans[Alg, Alg1]): Algebra1NatTrans[Alg, Alg2] =
    new Algebra1NatTrans[Alg, Alg2] {
      override def apply[F[_], A](alg: Alg[F, A]): Alg2[F, A] = self(alg3(alg))
    }
}

object Algebra1NatTrans {
  /**
    * The identity transformation of `Alg` to `Alg`
    */
  def id[Alg[_[_],_]]: Algebra1NatTrans[Alg, Alg] = new Algebra1NatTrans[Alg, Alg] {
    final override def apply[F[_], A](f: Alg[F, A]): Alg[F, A] = f
  }
}