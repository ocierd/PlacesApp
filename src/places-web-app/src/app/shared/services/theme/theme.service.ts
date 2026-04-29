import { Injectable, signal, Signal, WritableSignal } from '@angular/core';


type PlacesThemeType = 'places-light-theme' | 'places-dark-theme';

@Injectable({
  providedIn: 'root',
})
export class ThemeService {

  private themes: PlacesThemeType[] = ['places-light-theme', 'places-dark-theme'];
  private currentTheme: WritableSignal<PlacesThemeType> = signal('places-light-theme');

  getCurrentTheme(): Signal<PlacesThemeType> {
    return this.currentTheme.asReadonly();
  }

  constructor() {}

  setTheme(theme: PlacesThemeType): void {
    this.currentTheme.set(theme);
  }

  getThemes(): PlacesThemeType[] {
    return this.themes;
  }

}
