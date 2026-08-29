// @ts-nocheck
import { ResolvedRoute } from './interfaces/resolved-route.interface.js';
import { RouteConflictDetector } from './route-conflict-detector.js';

type SegmentKindOrMissing = 'literal' | 'param' | 'wildcard' | 'missing';

export class RouteSpecificitySorter {
  private static readonly SEGMENT_KIND_RANK: Record<
    SegmentKindOrMissing,
    number
  > = {
    literal: 0,
    param: 1,
    wildcard: 2,
    missing: 3,
  };

  public static sort(routes: ResolvedRoute[]): ResolvedRoute[] {
    const decoratedRoutes = routes.map((route, declarationIndex) => ({
      route,
      declarationIndex,
    }));

    decoratedRoutes.sort((leftEntry, rightEntry) => {
      const specificityDelta = RouteSpecificitySorter.comparePathSpecificity(
        leftEntry.route.path,
        rightEntry.route.path,
      );
      if (specificityDelta !== 0) return specificityDelta;
      return leftEntry.declarationIndex - rightEntry.declarationIndex;
    });

    return decoratedRoutes.map(decoratedEntry => decoratedEntry.route);
  }

  private static comparePathSpecificity(
    leftPath: string,
    rightPath: string,
  ): number {
    const leftSegments = RouteConflictDetector.tokenizePath(leftPath);
    const rightSegments = RouteConflictDetector.tokenizePath(rightPath);
    const longestPathLength = Math.max(
      leftSegments.length,
      rightSegments.length,
    );

    let specificityDelta = 0;

    Array.from({ length: longestPathLength }).forEach((_, segmentIndex) => {
      if (specificityDelta !== 0) return;

      const leftKind = leftSegments[segmentIndex]?.kind ?? 'missing';
      const rightKind = rightSegments[segmentIndex]?.kind ?? 'missing';

      const leftRank = RouteSpecificitySorter.rankSegmentByKind(leftKind);
      const rightRank = RouteSpecificitySorter.rankSegmentByKind(rightKind);

      if (leftRank !== rightRank) {
        specificityDelta = leftRank - rightRank;
      }
    });

    return specificityDelta;
  }

  private static rankSegmentByKind(kind: SegmentKindOrMissing): number {
    return RouteSpecificitySorter.SEGMENT_KIND_RANK[kind];
  }
}
